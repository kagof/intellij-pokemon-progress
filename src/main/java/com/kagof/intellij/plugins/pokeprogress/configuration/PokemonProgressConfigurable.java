package com.kagof.intellij.plugins.pokeprogress.configuration;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nls;

import com.intellij.openapi.options.Configurable;

public class PokemonProgressConfigurable implements Configurable {
    private PokemonProgressConfigurationComponent component;

    @Nls
    @Override
    public String getDisplayName() {
        return "Pokémon Progress";
    }

    @Override
    public JComponent createComponent() {
        component = new PokemonProgressConfigurationComponent();
        return component.getPanel();
    }

    @Override
    public boolean isModified() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        return component != null && (!state.pokemonNumbersEnabled.equals(component.getEnabledNumberMap())
            || state.drawSprites != component.getDrawSprites().isSelected()
            || state.addToolTips != component.getAddToolTips().isSelected()
            || state.initialVelocity != component.getInitialVelocity().getValue() / 100f
            || state.acceleration != component.getAcceleration().getValue() / 100f);
    }

    @Override
    public void apply() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        state.pokemonNumbersEnabled = component.getEnabledNumberMap();
        state.drawSprites = component.getDrawSprites().isSelected();
        state.addToolTips = component.getAddToolTips().isSelected();
        state.initialVelocity = component.getInitialVelocity().getValue() / 100f;
        state.acceleration = component.getAcceleration().getValue() / 100f;
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
