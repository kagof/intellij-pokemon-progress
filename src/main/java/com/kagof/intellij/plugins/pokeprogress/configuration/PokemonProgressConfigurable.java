package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.util.Objects;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nls;

import com.intellij.openapi.options.Configurable;

public class PokemonProgressConfigurable implements Configurable {
    private PokemonProgressConfigurationComponent component;

    @Nls
    @Override
    public String getDisplayName() {
        return "Pok\u00E9mon Progress";
    }

    @Override
    public JComponent createComponent() {
        component = new PokemonProgressConfigurationComponent();
        return component.getPanel();
    }

    @Override
    public boolean isModified() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        return component != null && (!state.pokemonNumbersEnabled.equals(component.getEnabledIdMap())
            || !Objects.equals(state.theme, component.getTheme().getItemAt(component.getTheme().getSelectedIndex()).getId())
            || !Objects.equals(state.colorScheme,
                component.getColorScheme().getItemAt(component.getColorScheme().getSelectedIndex()).getId())
            || state.drawSprites != component.getDrawSprites().isSelected()
            || state.addToolTips != component.getAddToolTips().isSelected()
            || state.addIconToToolTips != component.getAddIconToToolTips().isSelected()
            || state.transparencyOnIndeterminate != component.getIndeterminateTransparency().isSelected()
            || state.transparencyOnDeterminate != component.getDeterminateTransparency().isSelected()
            || state.initialVelocity != component.getInitialVelocity().getValue() / 100f
            || state.acceleration != component.getAcceleration().getValue() / 100f
            || state.isReplaceLoaderIcon() != component.getReplaceLoaderIcon().isSelected()
            || state.showUpdateNotification != component.getShowUpdateNotification().isSelected()
            || state.restrictMaximumHeight != component.getRestrictMaxHeight().isSelected()
            || state.maximumHeight != component.getMaxHeight().getValue()
            || state.restrictMinimumHeight != component.getRestrictMinHeight().isSelected()
            || state.minimumHeight != component.getMinHeight().getValue());
    }

    @Override
    public void apply() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        state.pokemonNumbersEnabled = component.getEnabledIdMap();
        state.theme = component.getTheme().getItemAt(component.getTheme().getSelectedIndex()).getId();
        state.colorScheme = component.getColorScheme().getItemAt(component.getColorScheme().getSelectedIndex()).getId();
        state.drawSprites = component.getDrawSprites().isSelected();
        state.addToolTips = component.getAddToolTips().isSelected();
        state.addIconToToolTips = component.getAddIconToToolTips().isSelected();
        state.transparencyOnIndeterminate = component.getIndeterminateTransparency().isSelected();
        state.transparencyOnDeterminate = component.getDeterminateTransparency().isSelected();
        state.initialVelocity = component.getInitialVelocity().getValue() / 100f;
        state.acceleration = component.getAcceleration().getValue() / 100f;
        state.setReplaceLoaderIcon(component.getReplaceLoaderIcon().isSelected());
        state.showUpdateNotification = component.getShowUpdateNotification().isSelected();
        state.restrictMaximumHeight = component.getRestrictMaxHeight().isSelected();
        state.restrictMinimumHeight = component.getRestrictMinHeight().isSelected();
        state.setHeightLimits(component.getMaxHeight().getValue(), component.getMinHeight().getValue());
    }

    @Override
    public void reset() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        component.updateUi(state);
    }

    @Override
    public void disposeUIResources() {
        component = null;
    }
}
