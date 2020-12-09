package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.roots.ScalableIconComponent;
import com.intellij.util.ui.FormBuilder;
import com.kagof.intellij.plugins.pokeprogress.Generation;
import com.kagof.intellij.plugins.pokeprogress.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.PokemonResourceLoader;

public class PokemonProgressConfigurationComponent {
    private JPanel mainPanel;
    private final JBCheckBox drawSprites = new JBCheckBox("Draw sprites");
    private final JBCheckBox addToolTips = new JBCheckBox("Add tool tips");
    private final Map<String, JBCheckBox> checkboxes = new HashMap<>();
    private final JButton selectAll = new JButton("Select all");
    private final JButton deselectAll = new JButton("Deselect all");
    private final JLabel label = new JLabel("?/? Pokémon selected");
    private final AtomicInteger numSelected = new AtomicInteger(0);

    public PokemonProgressConfigurationComponent() {
        createUi();
    }

    void createUi() {
        final FormBuilder formBuilder = FormBuilder.createFormBuilder();
        formBuilder.addComponent(drawSprites);
        drawSprites.setToolTipText("Not actually drawing the Pokémon icons can make your IDE look more professional");

        formBuilder.addComponent(addToolTips);
        addToolTips.setToolTipText("Whether or not to add a Pokémon tool tip (hover text) on the progress bars");

        formBuilder.addSeparator();
        formBuilder.addComponent(label);
        formBuilder.addComponent(selectAll);
        formBuilder.addComponent(deselectAll);
        selectAll.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                checkboxes.values().forEach(c -> c.setSelected(true));
            }
        });
        deselectAll.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                checkboxes.values().forEach(c -> c.setSelected(false));
            }
        });

        final Map<Generation, Boolean> gens = Arrays.stream(Generation.values()).collect(Collectors.toMap(Function.identity(), __ -> false));

        Pokemon.DEFAULT_POKEMON.values().stream().sorted().forEach(pokemon -> {
            final Generation gen = pokemon.getGeneration();
            if (!gens.get(gen)) {
                final JLabel genLabel = new JLabel("Generation " + gen);
                formBuilder.addComponent(genLabel);
                gens.put(gen, true);
            }
            final JBCheckBox checkBox = new JBCheckBox(pokemon.getNameWithNumber(), true);
            checkBox.addItemListener(c -> {
                if (c.getStateChange() == ItemEvent.SELECTED) {
                    numSelected.incrementAndGet();
                } else if (c.getStateChange() == ItemEvent.DESELECTED) {
                    numSelected.decrementAndGet();
                }
                refreshSelectAllButtons();
            });
            formBuilder.addLabeledComponent(new ScalableIconComponent(PokemonResourceLoader.getIcon(pokemon)), checkBox);
            checkboxes.put(pokemon.getNumberString(), checkBox);
            numSelected.incrementAndGet();
        });
        refreshSelectAllButtons();
        mainPanel = formBuilder.getPanel();
    }

    void updateUi(final PokemonProgressState state) {
        if (state != null) {
            drawSprites.setSelected(state.drawSprites);
            addToolTips.setSelected(state.addToolTips);
            state.pokemonNumbersEnabled
                .forEach((pokemon, enabled) -> checkboxes.computeIfPresent(pokemon, (p, check) -> {
                    check.setSelected(enabled);
                    return check;
                }));
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public Map<String, Boolean> getEnabledNumberMap() {
        return checkboxes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().isSelected()));
    }

    public JBCheckBox getDrawSprites() {
        return drawSprites;
    }

    public JBCheckBox getAddToolTips() {
        return addToolTips;
    }

    private void refreshSelectAllButtons() {
        final int i = numSelected.get();
        final int size = checkboxes.size();
        deselectAll.setEnabled(i > 0);
        selectAll.setEnabled(i < size);
        label.setText(i + "/" + size + " Pokémon selected");
    }

}