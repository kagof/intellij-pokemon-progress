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
        return component != null && (!state.getPokemonNumbersEnabled().equals(component.getEnabledNumberMap())
            || state.isDrawSprites() != component.getDrawSprites().isSelected()
            || state.isAddToolTips() != component.getAddToolTips().isSelected()
            || state.getInitialVelocity() != component.getInitialVelocity().getValue() / 100f
            || state.getAcceleration() != component.getAcceleration().getValue() / 100f);
    }

    @Override
    public void apply() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        state.setPokemonNumbersEnabled(component.getEnabledNumberMap());
        state.setDrawSprites(component.getDrawSprites().isSelected());
        state.setAddToolTips(component.getAddToolTips().isSelected());
        state.setInitialVelocity(component.getInitialVelocity().getValue() / 100f);
        state.setAcceleration(component.getAcceleration().getValue() / 100f);
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
