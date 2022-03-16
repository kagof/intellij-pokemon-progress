package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.AnimatedIcon;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.roots.ScalableIconComponent;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.ThreeStateCheckBox;
import com.intellij.util.ui.ThreeStateCheckBox.State;
import com.kagof.intellij.plugins.pokeprogress.PokeballLoaderIconReplacer;
import com.kagof.intellij.plugins.pokeprogress.PokemonPicker;
import com.kagof.intellij.plugins.pokeprogress.PokemonProgressBarUi;
import com.kagof.intellij.plugins.pokeprogress.PokemonResourceLoader;
import com.kagof.intellij.plugins.pokeprogress.model.Generation;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorScheme;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorSchemes;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintTheme;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintThemes;

public class PokemonProgressConfigurationComponent {
    private JPanel mainPanel;
    private final JComboBox<PaintTheme> theme = new ComboBox<>(PaintThemes.getAll());
    private final JComboBox<ColorScheme> colorScheme = new ComboBox<>(ColorSchemes.getAll());
    private final JBCheckBox replaceLoaderIcon = new JBCheckBox("Replace loader icon with Pokéball");
    final JLabel loader = new JLabel(new AnimatedIcon.Default());
    private final JBCheckBox drawSprites = new JBCheckBox("Draw sprites");
    private final JBCheckBox addToolTips = new JBCheckBox("Add tool tips");
    private final JBCheckBox indeterminateTransparency = new JBCheckBox("Transparency on indeterminate");
    private final JBCheckBox determinateTransparency = new JBCheckBox("Transparency on determinate");
    private final Map<String, JBCheckBox> checkboxes = new HashMap<>();
    private final Multimap<Generation, JCheckBox> checkboxesByGen = ArrayListMultimap.create();
    private final Map<Generation, ThreeStateCheckBox> genToggleCheckBoxes = new EnumMap<>(Generation.class);
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
        final JPanel themes = new JPanel();
        themes.setLayout(new GridLayout(1, 2));
        themes.add(LabeledComponent.create(theme, "Theme:"));
        themes.add(LabeledComponent.create(colorScheme, "Color scheme:"));
        formBuilder.addComponent(themes);
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

        final Set<Generation> addedGens = EnumSet.noneOf(Generation.class);

        Pokemon.DEFAULT_POKEMON.values().stream().sorted().forEach(pokemon -> {
            final Generation gen = pokemon.getGeneration();

            if (addedGens.add(gen)) {
                final ThreeStateCheckBox genToggleCheckBox = new ThreeStateCheckBox("Generation " + gen);
                genToggleCheckBox.setThirdStateEnabled(false);
                genToggleCheckBox.addItemListener(c -> {
                    final boolean isSelected = genToggleCheckBox.getState() == State.SELECTED;
                    checkboxesByGen.get(gen).forEach(cb -> cb.setSelected(isSelected));
                    refreshSelectAllButtons();
                });
                formBuilder.addComponent(genToggleCheckBox);
                genToggleCheckBoxes.put(gen, genToggleCheckBox);
            }

            final JBCheckBox checkBox = new JBCheckBox(pokemon.getNameWithNumber(), true);
            checkBox.addItemListener(c -> {
                if (c.getStateChange() == ItemEvent.SELECTED) {
                    numSelected.incrementAndGet();
                } else if (c.getStateChange() == ItemEvent.DESELECTED) {
                    numSelected.decrementAndGet();
                }
                refreshGenCheckBox(gen);
                refreshSelectAllButtons();
            });

            formBuilder.addLabeledComponent(new ScalableIconComponent(PokemonResourceLoader.getIcon(pokemon)), checkBox);
            checkboxes.put(pokemon.getId(), checkBox);
            checkboxesByGen.put(gen, checkBox);
            numSelected.incrementAndGet();
        });

        addedGens.forEach(this::refreshGenCheckBox);
        refreshSelectAllButtons();
        mainPanel = formBuilder.getPanel();
    }

    @NotNull
    private JPanel createCheckboxPanel() {
        final JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(3, 2));

        checkboxPanel.add(drawSprites);
        drawSprites.setToolTipText("Not actually drawing the Pokémon icons can make your IDE look more professional");
        checkboxPanel.add(indeterminateTransparency);

        checkboxPanel.add(addToolTips);
        addToolTips.setToolTipText("Whether or not to add a Pokémon tool tip (hover text) on the progress bars");
        checkboxPanel.add(determinateTransparency);

        replaceLoaderIcon.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                PokeballLoaderIconReplacer.updateSpinner(replaceLoaderIcon.isSelected());
                loader.setIcon(new AnimatedIcon.Default());
            }
        });
        checkboxPanel.add(replaceLoaderIcon);
        return checkboxPanel;
    }

    void updateUi(final PokemonProgressState state) {
        if (state != null) {
            initialVelocity.setValue((int) (state.initialVelocity * 100));
            acceleration.setValue((int) (state.acceleration * 100));
            theme.setSelectedItem(PaintThemes.getByIdOrDefault(state.theme));
            colorScheme.setSelectedItem(ColorSchemes.getByIdOrDefault(state.colorScheme));
            drawSprites.setSelected(state.drawSprites);
            addToolTips.setSelected(state.addToolTips);
            indeterminateTransparency.setSelected(state.transparencyOnIndeterminate);
            determinateTransparency.setSelected(state.transparencyOnDeterminate);
            state.pokemonNumbersEnabled
                .forEach((id, enabled) -> checkboxes.computeIfPresent(id, (p, check) -> {
                    check.setSelected(enabled);
                    return check;
                }));
            replaceLoaderIcon.setSelected(state.isReplaceLoaderIcon());
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public Map<String, Boolean> getEnabledIdMap() {
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

    public JComboBox<PaintTheme> getTheme() {
        return theme;
    }

    public JComboBox<ColorScheme> getColorScheme() {
        return colorScheme;
    }

    public JBCheckBox getIndeterminateTransparency() {
        return indeterminateTransparency;
    }

    public JBCheckBox getDeterminateTransparency() {
        return determinateTransparency;
    }

    public JBCheckBox getReplaceLoaderIcon() {
        return replaceLoaderIcon;
    }

    private void refreshSelectAllButtons() {
        final int i = numSelected.get();
        final int size = checkboxes.size();
        deselectAll.setEnabled(i > 0);
        selectAll.setEnabled(i < size);
        label.setText(i + "/" + size + " Pokémon selected");
    }

    private void refreshGenCheckBox(final Generation gen) {
        final ThreeStateCheckBox toggleCheckBox = genToggleCheckBoxes.get(gen);
        final Collection<JCheckBox> pokemonCheckBoxes = checkboxesByGen.get(gen);
        final boolean areAllSelected = pokemonCheckBoxes.stream().allMatch(JCheckBox::isSelected);
        final boolean areNoneSelected = pokemonCheckBoxes.stream().noneMatch(JCheckBox::isSelected);

        if (areAllSelected) {
            toggleCheckBox.setState(State.SELECTED);
        } else if (areNoneSelected) {
            toggleCheckBox.setState(State.NOT_SELECTED);
        } else {
            toggleCheckBox.setState(State.DONT_CARE);
        }
    }

    private JPanel createPreviewPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        final JProgressBar determinateProgressBar = new JProgressBar(0, 2);
        determinateProgressBar.setIndeterminate(false);
        determinateProgressBar.setValue(1);
        determinateProgressBar.setUI(createProgressBarUi());

        final JProgressBar indeterminateProgressBar = new JProgressBar();
        indeterminateProgressBar.setIndeterminate(true);
        indeterminateProgressBar.setUI(createProgressBarUi());

        final JButton randomizeButton = new JButton(AllIcons.Actions.Refresh);
        randomizeButton.setToolTipText("Randomize");
        randomizeButton.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                determinateProgressBar.setUI(createProgressBarUi());
                indeterminateProgressBar.setUI(createProgressBarUi());
            }
        });

        loader.setToolTipText("loader/spinner icon");

        final GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.gridheight = 1;
        buttonConstraints.weightx = 0;
        panel.add(randomizeButton, buttonConstraints);
        final GridBagConstraints loaderConstraints = new GridBagConstraints();
        loaderConstraints.gridx = GridBagConstraints.RELATIVE;
        loaderConstraints.gridy = 0;
        loaderConstraints.gridwidth = 1;
        loaderConstraints.gridheight = 1;
        loaderConstraints.weightx = 0.1;
        panel.add(LabeledComponent.create(loader, "Loader", BorderLayout.NORTH), loaderConstraints);
        final GridBagConstraints progressBarConstraints = new GridBagConstraints();
        progressBarConstraints.gridx = GridBagConstraints.RELATIVE;
        progressBarConstraints.gridy = 0;
        progressBarConstraints.gridwidth = 3;
        progressBarConstraints.gridheight = 1;
        progressBarConstraints.weightx = 0.45;
        progressBarConstraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(LabeledComponent.create(determinateProgressBar, "Determinate", BorderLayout.NORTH), progressBarConstraints);
        panel.add(LabeledComponent.create(indeterminateProgressBar, "Indeterminate", BorderLayout.NORTH), progressBarConstraints);
        return panel;
    }

    private PokemonProgressBarUi createProgressBarUi() {
        return new PokemonProgressBarUi(PokemonPicker.get(),
            () -> initialVelocity.getValue() / 100f,
            () -> acceleration.getValue() / 100f,
            () -> theme.getItemAt(theme.getSelectedIndex()),
            () -> colorScheme.getItemAt(colorScheme.getSelectedIndex()),
            indeterminateTransparency::isSelected,
            determinateTransparency::isSelected,
            drawSprites::isSelected,
            addToolTips::isSelected);
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
