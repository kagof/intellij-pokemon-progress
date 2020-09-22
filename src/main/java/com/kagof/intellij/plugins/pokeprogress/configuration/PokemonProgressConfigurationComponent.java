package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.roots.ScalableIconComponent;
import com.intellij.util.ui.FormBuilder;
import com.kagof.intellij.plugins.pokeprogress.Pokemon;

public class PokemonProgressConfigurationComponent {
    private JPanel mainPanel;
    private final Map<String, JBCheckBox> checkboxes = new HashMap<>();
    private final JButton selectAll = new JButton("Select all");
    private final JButton deselectAll = new JButton("Deselect all");
    private final JLabel label = new JLabel("?/? selected");
    private final AtomicInteger numSelected = new AtomicInteger(0);


    public PokemonProgressConfigurationComponent() {
        createUi();
    }

    void createUi() {
        final FormBuilder formBuilder = FormBuilder.createFormBuilder();
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
        formBuilder.addComponent(label);
        formBuilder.addComponent(selectAll);
        formBuilder.addComponent(deselectAll);

        Pokemon.DEFAULT_POKEMON.values().stream().sorted().forEach(pokemon -> {
            final JBCheckBox checkBox = new JBCheckBox(pokemon.getNameWithNumber(), true);
            checkBox.addItemListener(c -> {
                if (c.getStateChange() == ItemEvent.SELECTED) {
                    numSelected.incrementAndGet();
                } else if (c.getStateChange() == ItemEvent.DESELECTED){
                    numSelected.decrementAndGet();
                }
                refreshSelectAllButtons();
            });
            formBuilder.addLabeledComponent(new ScalableIconComponent(pokemon.getIcon()), checkBox);
            checkboxes.put(pokemon.getNumber(), checkBox);
            numSelected.incrementAndGet();
        });
        refreshSelectAllButtons();
        mainPanel = formBuilder.getPanel();
    }

    void updateUi(final PokemonProgressState state) {
        if (state != null) {
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

    private void refreshSelectAllButtons() {
        final int i = numSelected.get();
        deselectAll.setEnabled(i > 0);
        selectAll.setEnabled(i < checkboxes.size());
        label.setText(i + "/" + checkboxes.size() + " selected");
    }

}