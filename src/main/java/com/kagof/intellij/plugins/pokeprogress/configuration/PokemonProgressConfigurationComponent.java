package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.awt.BorderLayout;
import java.awt.Font;
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
import java.util.Optional;
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
import com.intellij.ui.components.DefaultLinkButtonUI;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.roots.ScalableIconComponent;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.ThreeStateCheckBox;
import com.intellij.util.ui.ThreeStateCheckBox.State;
import com.kagof.intellij.plugins.pokeprogress.PokeballLoaderIconReplacer;
import com.kagof.intellij.plugins.pokeprogress.PokemonPicker;
import com.kagof.intellij.plugins.pokeprogress.PokemonProgressBarUi;
import com.kagof.intellij.plugins.pokeprogress.PokemonProgressChangenotesDialog;
import com.kagof.intellij.plugins.pokeprogress.PokemonResourceLoader;
import com.kagof.intellij.plugins.pokeprogress.UpdateNotificationActivity;
import com.kagof.intellij.plugins.pokeprogress.model.Generation;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorScheme;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorSchemes;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintTheme;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintThemes;

public class PokemonProgressConfigurationComponent {
    private JPanel mainPanel;
    final JLabel title = new JLabel("Pok\u00E9mon Progress");
    final JProgressBar determinateProgressBar = new JProgressBar(0, 2);
    final JProgressBar indeterminateProgressBar = new JProgressBar();
    private PokemonProgressBarUi determinateUi;
    private PokemonProgressBarUi indeterminateUi;
    private final JComboBox<PaintTheme> theme = new ComboBox<>(PaintThemes.getAll());
    private final JComboBox<ColorScheme> colorScheme = new ComboBox<>(ColorSchemes.getAll());
    private final JBCheckBox replaceLoaderIcon = new JBCheckBox("Replace loader icon with Pokéball");
    final JLabel loader = new JLabel(new AnimatedIcon.Default());
    private final JBCheckBox drawSprites = new JBCheckBox("Draw sprites");
    private final JBCheckBox addToolTips = new JBCheckBox("Add tool tips");
    private final JBCheckBox addIconToToolTips = new JBCheckBox("Add icons to tool tips");
    private final JBCheckBox indeterminateTransparency = new JBCheckBox("Transparency on indeterminate");
    private final JBCheckBox determinateTransparency = new JBCheckBox("Transparency on determinate");
    private final JBCheckBox showUpdateNotification = new JBCheckBox("Show update notification");
    private final Map<String, JBCheckBox> checkboxes = new HashMap<>();
    private final Multimap<Generation, JCheckBox> checkboxesByGen = ArrayListMultimap.create();
    private final Map<Generation, ThreeStateCheckBox> genToggleCheckBoxes = new EnumMap<>(Generation.class);
    private final JButton selectAll = new JButton("Select all");
    private final JButton deselectAll = new JButton("Deselect all");
    private final JLabel label = new JLabel("?/? Pokémon selected");
    private final AtomicInteger numSelected = new AtomicInteger(0);
    private final JSlider initialVelocity = new JSlider(1, 500, 100);
    private final JSlider acceleration = new JSlider(1, 500, 40);
    private final JBCheckBox restrictMaxHeight = new JBCheckBox("Restrict max height");
    private final JSlider maxHeight = new JSlider(8, 64, 20);
    private final JBCheckBox restrictMinHeight = new JBCheckBox("Restrict min height");
    private final JSlider minHeight = new JSlider(8, 64, 20);

    public PokemonProgressConfigurationComponent() {
        createUi();
    }

    void createUi() {
        final FormBuilder formBuilder = FormBuilder.createFormBuilder();
        formBuilder.addComponent(createTitlePanel());
        formBuilder.addVerticalGap(5);
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
        formBuilder.addComponent(createHeightPanel());

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

    private JPanel createTitlePanel() {
        final JButton changenotes = new JButton("Changenotes");
        changenotes.setUI(DefaultLinkButtonUI.createUI(changenotes));
        changenotes.addActionListener(a -> new PokemonProgressChangenotesDialog(null).show());
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        final GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        left.weightx = 0.5;
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        titlePanel.add(title, left);
        final GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;
        right.weightx = 0.5;
        titlePanel.add(changenotes, right);
        return titlePanel;
    }

    private JPanel createHeightPanel() {
        final JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new GridLayout(2, 2));
        heightPanel.add(restrictMaxHeight);
        heightPanel.add(restrictMinHeight);
        heightPanel.add(maxHeight);
        heightPanel.add(minHeight);
        setupHeightConfig(restrictMaxHeight, maxHeight, "Restrict max height");
        setupHeightConfig(restrictMinHeight, minHeight, "Restrict min height");
        maxHeight.addChangeListener(c -> {
            if (maxHeight.getValue() < minHeight.getValue()) {
                minHeight.setValue(maxHeight.getValue());
            }
        });
        minHeight.addChangeListener(c -> {
            if (minHeight.getValue() > maxHeight.getValue()) {
                maxHeight.setValue(minHeight.getValue());
            }
        });
        return heightPanel;
    }

    private void setupHeightConfig(final JBCheckBox checkbox, final JSlider slider, final String text) {
        checkbox.addItemListener(c -> {
            if (c.getStateChange() == ItemEvent.SELECTED) {
                slider.setEnabled(true);
                checkbox.setText(text + ": " + slider.getValue() + "px");
                if (determinateUi != null) {
                    determinateUi.computeScaledIcons();
                }
                if (indeterminateUi != null) {
                    indeterminateUi.computeScaledIcons();
                }
            } else if (c.getStateChange() == ItemEvent.DESELECTED) {
                slider.setEnabled(false);
                checkbox.setText(text);
            }
            determinateProgressBar.setUI(determinateUi);
            indeterminateProgressBar.setUI(indeterminateUi);
        });
        slider.setEnabled(false);
        slider.addChangeListener(c -> {
            if (slider.isEnabled()) {
                checkbox.setText(text + ": " + slider.getValue() + "px");
            }
            if (determinateUi != null) {
                determinateUi.computeScaledIcons();
                determinateProgressBar.setUI(determinateUi);
            }
            if (indeterminateUi != null) {
                indeterminateUi.computeScaledIcons();
                indeterminateProgressBar.setUI(indeterminateUi);
            }
        });
    }

    @NotNull
    private JPanel createCheckboxPanel() {
        final JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(3, 3));

        checkboxPanel.add(drawSprites);
        drawSprites.setToolTipText("If disabled, progress bars will just show the type colors");
        checkboxPanel.add(indeterminateTransparency);

        checkboxPanel.add(determinateTransparency);
        checkboxPanel.add(addToolTips);
        addToolTips.setToolTipText("Whether or not to add a Pokémon tool tip (hover text) on the progress bars");
        addToolTips.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                addIconToToolTips.setEnabled(addToolTips.isSelected());
            }
        });
        checkboxPanel.add(addIconToToolTips);


        replaceLoaderIcon.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                PokeballLoaderIconReplacer.updateSpinner(replaceLoaderIcon.isSelected());
                loader.setIcon(new AnimatedIcon.Default());
            }
        });
        checkboxPanel.add(replaceLoaderIcon);

        showUpdateNotification.setToolTipText("Turn on or off the notification when the plugin has been updated");
        checkboxPanel.add(showUpdateNotification);

        return checkboxPanel;
    }

    void updateUi(final PokemonProgressState state) {
        if (state != null) {
            Optional.ofNullable(UpdateNotificationActivity.getPluginDescriptor())
                .ifPresent(desc -> title.setText("Pok\u00E9mon Progress " + desc.getVersion()));
            initialVelocity.setValue((int) (state.initialVelocity * 100));
            acceleration.setValue((int) (state.acceleration * 100));
            theme.setSelectedItem(PaintThemes.getByIdOrDefault(state.theme));
            colorScheme.setSelectedItem(ColorSchemes.getByIdOrDefault(state.colorScheme));
            drawSprites.setSelected(state.drawSprites);
            addToolTips.setSelected(state.addToolTips);
            addIconToToolTips.setSelected(state.addIconToToolTips);
            addIconToToolTips.setEnabled(addToolTips.isSelected());
            indeterminateTransparency.setSelected(state.transparencyOnIndeterminate);
            determinateTransparency.setSelected(state.transparencyOnDeterminate);
            state.pokemonNumbersEnabled
                .forEach((id, enabled) -> checkboxes.computeIfPresent(id, (p, check) -> {
                    check.setSelected(enabled);
                    return check;
                }));
            replaceLoaderIcon.setSelected(state.isReplaceLoaderIcon());
            showUpdateNotification.setSelected(state.showUpdateNotification);
            maxHeight.setValue(state.maximumHeight);
            minHeight.setValue(state.minimumHeight);
            restrictMaxHeight.setSelected(state.restrictMaximumHeight);
            restrictMinHeight.setSelected(state.restrictMinimumHeight);
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

    public JBCheckBox getAddIconToToolTips() {
        return addIconToToolTips;
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

    public JBCheckBox getShowUpdateNotification() {
        return showUpdateNotification;
    }

    public JBCheckBox getRestrictMaxHeight() {
        return restrictMaxHeight;
    }

    public JSlider getMaxHeight() {
        return maxHeight;
    }

    public JBCheckBox getRestrictMinHeight() {
        return restrictMinHeight;
    }

    public JSlider getMinHeight() {
        return minHeight;
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

        determinateProgressBar.setIndeterminate(false);
        determinateProgressBar.setValue(1);
        determinateUi = createProgressBarUi();
        determinateProgressBar.setUI(determinateUi);

        indeterminateProgressBar.setIndeterminate(true);
        indeterminateUi = createProgressBarUi();
        indeterminateProgressBar.setUI(indeterminateUi);

        final JButton randomizeButton = new JButton(AllIcons.Actions.Refresh);
        randomizeButton.setToolTipText("Randomize");
        randomizeButton.addActionListener(a -> {
            if (a.getID() == ActionEvent.ACTION_PERFORMED) {
                determinateUi = createProgressBarUi();
                indeterminateUi = createProgressBarUi();
                determinateProgressBar.setUI(determinateUi);
                indeterminateProgressBar.setUI(indeterminateUi);
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
            addToolTips::isSelected,
            addIconToToolTips::isSelected,
            restrictMaxHeight::isSelected,
            maxHeight::getValue,
            restrictMinHeight::isSelected,
            minHeight::getValue);
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
