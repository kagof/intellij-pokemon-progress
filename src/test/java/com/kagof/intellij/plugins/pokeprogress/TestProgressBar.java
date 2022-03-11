package com.kagof.intellij.plugins.pokeprogress;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.intellij.ide.ui.laf.darcula.ui.DarculaTextBorder;
import com.intellij.mock.MockApplication;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.components.fields.IntegerField;
import com.intellij.util.ReflectionUtil;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorScheme;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorSchemes;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintTheme;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintThemes;

public class TestProgressBar {
    private static final int MAX_SHIFT_VALUE = 900;
    private PokemonProgressState state;

    private JFrame frame;
    private IntegerField xShift;
    private IntegerField yShift;
    private JProgressBar progressBar;

    private Pokemon selectedPokemon;
    private int originalXShift = 0;
    private int originalYShift = 0;

    @SuppressWarnings("FieldCanBeLocal")
    private final Pokemon target = null;

    @SuppressWarnings("ConstantConditions")
    public TestProgressBar() {
        setUpMockApplication();
        updateSelectedPokemon(Optional.ofNullable(target).orElseGet(PokemonPicker::get));
        initializeFrame();
        addShutdownHook();
    }

    private void setUpMockApplication() {
        state = new PokemonProgressState();
        state.drawSprites = true;
        state.addToolTips = false;
        final Disposable parent = () -> { /*do nothing*/ };
        final MockApplication application = MockApplication.setUp(parent);
        application.registerService(PokemonProgressState.class, state);
        ApplicationManager.setApplication(application, parent);
    }

    private void initializeFrame() {
        final JPanel contentPanel = createContentPanel();
        frame = new JFrame();
        frame.setTitle("Pokémon ProgressBar TestUI");
        frame.setContentPane(contentPanel);
        frame.setSize(contentPanel.getPreferredSize());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }

    private JPanel createContentPanel() {
        final JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(400, 250));
        contentPanel.setLayout(new GridLayout(5, 1));

        contentPanel.add(createPokemonComboBox());
        contentPanel.add(createProgressBar());
        contentPanel.add(createThemePanel());
        contentPanel.add(createShiftPanel());
        contentPanel.add(createButtonPanel());

        return contentPanel;
    }

    private JPanel createThemePanel() {
        final JPanel themePanel = new JPanel();
        themePanel.setLayout(new GridLayout(1, 2));
        final ComboBox<PaintTheme> paintThemeComboBox = new ComboBox<>(PaintThemes.getAll());
        final ComboBox<ColorScheme> colorSchemeComboBox = new ComboBox<>(ColorSchemes.getAll());
        paintThemeComboBox.addActionListener(e -> {
            state.theme = ((PaintTheme) ((ComboBox<?>) e.getSource()).getSelectedItem()).getId();
            updatePositionAndUI(e);
        });
        colorSchemeComboBox.addActionListener(e -> {
            state.colorScheme = ((ColorScheme) ((ComboBox<?>) e.getSource()).getSelectedItem()).getId();
            updatePositionAndUI(e);
        });

        themePanel.add(LabeledComponent.create(paintThemeComboBox, "Paint theme", BorderLayout.NORTH));
        themePanel.add(LabeledComponent.create(colorSchemeComboBox, "Color theme", BorderLayout.NORTH));
        return themePanel;
    }

    @SuppressWarnings("unchecked")
    private ComboBox<Pokemon> createPokemonComboBox() {
        final ComboBox<Pokemon> pokemonComboBox = new ComboBox<>(Pokemon.values());
        pokemonComboBox.setSelectedItem(selectedPokemon);
        pokemonComboBox.addActionListener(e -> {
            printIfShiftUpdated();
            updateSelectedPokemon(Objects.requireNonNull((Pokemon) ((ComboBox<Pokemon>) e.getSource()).getSelectedItem()));
            resetShifts();
            updatePositionAndUI(e);
        });
        return pokemonComboBox;
    }

    private JProgressBar createProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setUI(new PokemonProgressBarUi(selectedPokemon));
        progressBar.setMinimum(0);
        progressBar.setMaximum(2);
        progressBar.setValue(1);
        return progressBar;
    }

    private JPanel createShiftPanel() {
        final JPanel shiftPanel = new JPanel();
        shiftPanel.setLayout(new GridLayout(1, 2));
        xShift = createIntegerFieldTextBox("xShift");
        yShift = createIntegerFieldTextBox("yShift");

        shiftPanel.add(LabeledComponent.create(xShift, "X shift", BorderLayout.NORTH));
        shiftPanel.add(LabeledComponent.create(yShift, "Y shift", BorderLayout.NORTH));
        resetShifts();
        return shiftPanel;
    }

    private IntegerField createIntegerFieldTextBox(final String valueName) {
        final IntegerField shiftField = new IntegerField(valueName, -MAX_SHIFT_VALUE, MAX_SHIFT_VALUE);
        shiftField.setBorder(new DarculaTextBorder());
        shiftField.addActionListener(this::updatePositionAndUI);
        return shiftField;
    }

    private JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        final JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::updatePositionAndUI);

        final JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetShifts();
            updatePositionAndUI(e);
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(resetButton);

        return buttonPanel;
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                printIfShiftUpdated();
                super.run();
            }
        });
    }

    private void updateSelectedPokemon(final Pokemon newPokemon) {
        if (selectedPokemon != null) {
            setSelectedPokemonIntField("xShift", originalXShift);
            setSelectedPokemonIntField("yShift", originalYShift);
        }
        selectedPokemon = newPokemon;
        originalXShift = newPokemon.getXShift();
        originalYShift = newPokemon.getYShift();
    }

    private void resetShifts() {
        xShift.setValue(originalXShift);
        yShift.setValue(originalYShift);
        xShift.setDefaultValue(originalXShift);
        yShift.setDefaultValue(originalYShift);
    }

    private void updatePositionAndUI(final ActionEvent e) {
        if (e.getID() == ActionEvent.ACTION_PERFORMED) {
            handleShiftChange(xShift);
            handleShiftChange(yShift);
            IconLoader.clearCache();
            progressBar.setUI(new PokemonProgressBarUi(selectedPokemon));
            frame.repaint();
        }
    }

    private void handleShiftChange(final IntegerField field) {
        try {
            field.validateContent();
        } catch (final ConfigurationException ex) {
            field.setToolTipText(ex.getMessage());
            return;
        }
        field.setToolTipText(null);
        setSelectedPokemonIntField(field.getValueName(), field.getValue());
    }

    private void setSelectedPokemonIntField(final String name, final int amount) {
        try {
            final Field field = ReflectionUtil.findField(Pokemon.class, Integer.TYPE, name);
            if ((int) field.get(selectedPokemon) != amount) {
                field.setAccessible(true);
                field.setInt(selectedPokemon, amount);
                field.setAccessible(false);
            }
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void printIfShiftUpdated() {
        if (shiftUpdated()) {
            System.out.printf("%nUpdated shift for %s: %d, %d%n", selectedPokemon.getNameWithNumber(), xShift.getValue(), yShift.getValue());
        }
    }

    private boolean shiftUpdated() {
        return !Objects.equals(originalXShift, selectedPokemon.getXShift())
            || !Objects.equals(originalYShift, selectedPokemon.getYShift());
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(TestProgressBar::new);
    }
}
