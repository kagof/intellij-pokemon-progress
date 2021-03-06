package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JProgressBar;
import javax.swing.JSlider;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.roots.ScalableIconComponent;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.FormBuilder;
import com.kagof.intellij.plugins.pokeprogress.Generation;
import com.kagof.intellij.plugins.pokeprogress.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.PokemonPicker;
import com.kagof.intellij.plugins.pokeprogress.PokemonProgressBarUi;
import com.kagof.intellij.plugins.pokeprogress.PokemonResourceLoader;

public class PokemonProgressConfigurationComponent {
    private JPanel mainPanel;
    private final JBCheckBox drawSprites = new JBCheckBox("Draw sprites");
    private final JBCheckBox addToolTips = new JBCheckBox("Add tool tips");
    private final JBCheckBox indeterminateTransparency = new JBCheckBox("Transparency on indeterminate");
    private final JBCheckBox determinateTransparency = new JBCheckBox("Transparency on determinate");
    private final Map<String, JBCheckBox> checkboxes = new HashMap<>();
    private final JButton selectAll = new JButton("Select all");
    private final JButton deselectAll = new JButton("Deselect all");
    private final JLabel label = new JLabel("?/? Pokémon selected");
    private final AtomicInteger numSelected = new AtomicInteger(0);
    private final JSlider initialVelocity = new JSlider(1, 500, 100);
    private final JSlider acceleration = new JSlider(1, 500, 40);

    public PokemonProgressConfigurationComponent() {
        createUi();
    }

    void createUi() {
        final FormBuilder formBuilder = FormBuilder.createFormBuilder();
        formBuilder.addLabeledComponent("Preview", createPreviewPanel(), true);
        formBuilder.addSeparator();
        formBuilder.addComponent(createIndeterminatePanel());
        formBuilder.addSeparator();
        formBuilder.addComponent(createCheckboxPanel());

        formBuilder.addSeparator();
        formBuilder.addVerticalGap(5);
        formBuilder.addComponent(label);
        final JPanel selectButtonsPanel = new JPanel();
        selectButtonsPanel.setLayout(new GridLayout(1, 2));
        selectButtonsPanel.add(selectAll);
        selectButtonsPanel.add(deselectAll);
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
        formBuilder.addComponent(selectButtonsPanel);

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

    @NotNull
    private JPanel createCheckboxPanel() {
        final JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(2, 2));

        checkboxPanel.add(drawSprites);
        drawSprites.setToolTipText("Not actually drawing the Pokémon icons can make your IDE look more professional");
        checkboxPanel.add(indeterminateTransparency);

        checkboxPanel.add(addToolTips);
        addToolTips.setToolTipText("Whether or not to add a Pokémon tool tip (hover text) on the progress bars");
        checkboxPanel.add(determinateTransparency);
        return checkboxPanel;
    }

    void updateUi(final PokemonProgressState state) {
        if (state != null) {
            initialVelocity.setValue((int) (state.initialVelocity * 100));
            acceleration.setValue((int) (state.acceleration * 100));
            drawSprites.setSelected(state.drawSprites);
            addToolTips.setSelected(state.addToolTips);
            indeterminateTransparency.setSelected(state.transparencyOnIndeterminate);
            determinateTransparency.setSelected(state.transparencyOnDeterminate);
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

    public JSlider getInitialVelocity() {
        return initialVelocity;
    }

    public JSlider getAcceleration() {
        return acceleration;
    }

    public JBCheckBox getIndeterminateTransparency() {
        return indeterminateTransparency;
    }

    public JBCheckBox getDeterminateTransparency() {
        return determinateTransparency;
    }

    private void refreshSelectAllButtons() {
        final int i = numSelected.get();
        final int size = checkboxes.size();
        deselectAll.setEnabled(i > 0);
        selectAll.setEnabled(i < size);
        label.setText(i + "/" + size + " Pokémon selected");
    }

    private JPanel createPreviewPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        final JProgressBar determinateProgressBar = new JProgressBar(0, 2);
        determinateProgressBar.setIndeterminate(false);
        determinateProgressBar.setValue(1);
        determinateProgressBar.setUI(new PokemonProgressBarUi(PokemonPicker.get(),
            () -> initialVelocity.getValue() / 100f,
            () -> acceleration.getValue() / 100f,
            indeterminateTransparency::isSelected,
            determinateTransparency::isSelected,
            drawSprites::isSelected,
            addToolTips::isSelected));

        final JProgressBar indeterminateProgressBar = new JProgressBar();
        indeterminateProgressBar.setIndeterminate(true);
        indeterminateProgressBar.setUI(new PokemonProgressBarUi(PokemonPicker.get(),
            () -> initialVelocity.getValue() / 100f,
            () -> acceleration.getValue() / 100f,
            indeterminateTransparency::isSelected,
            determinateTransparency::isSelected,
            drawSprites::isSelected,
            addToolTips::isSelected));

        panel.add(LabeledComponent.create(determinateProgressBar, "Determinate", BorderLayout.NORTH));
        panel.add(LabeledComponent.create(indeterminateProgressBar, "Indeterminate", BorderLayout.NORTH));
        return panel;
    }

    private JPanel createIndeterminatePanel() {
        final JPanel indeterminatePanel = new JPanel();
        indeterminatePanel.setLayout(new GridLayout(2, 2));
        final LabeledComponent<JSlider> labeledInitVelocity = LabeledComponent
            .create(initialVelocity, String.format("Indeterminate initial velocity (%d/%d)", initialVelocity.getValue(), initialVelocity.getMaximum()));
        indeterminatePanel.add(labeledInitVelocity);
        indeterminatePanel.add(new Spacer());
        final LabeledComponent<JSlider> labeledAccel = LabeledComponent
            .create(acceleration, String.format("Indeterminate acceleration (%d/%d)", acceleration.getValue(), acceleration.getMaximum()));
        indeterminatePanel.add(labeledAccel);
        final JButton resetIndeterminateButton = new JButton("Reset to defaults");
        resetIndeterminateButton.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                acceleration.setValue(40);
                initialVelocity.setValue(100);
            }
        });
        initialVelocity.addChangeListener(e -> labeledInitVelocity.getLabel()
            .setText(String.format("Indeterminate initial velocity (%d/%d)", initialVelocity.getValue(), initialVelocity.getMaximum())));
        acceleration.addChangeListener(e -> labeledAccel.getLabel()
            .setText(String.format("Indeterminate acceleration (%d/%d)", acceleration.getValue(), acceleration.getMaximum())));
        indeterminatePanel.add(resetIndeterminateButton);
        return indeterminatePanel;
    }

}