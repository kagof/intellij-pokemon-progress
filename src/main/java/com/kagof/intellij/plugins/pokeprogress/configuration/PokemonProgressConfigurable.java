package com.kagof.intellij.plugins.pokeprogress.configuration;

import javax.swing.JComponent;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts;

public class PokemonProgressConfigurable implements Configurable {
    private PokemonProgressConfigurationComponent component;

    @Override
    @NlsContexts.ConfigurableName
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
            || state.addToolTips != component.getAddToolTips().isSelected());
    }

    @Override
    public void apply() {
        final PokemonProgressState state = PokemonProgressState.getInstance();
        state.pokemonNumbersEnabled = component.getEnabledNumberMap();
        state.drawSprites = component.getDrawSprites().isSelected();
        state.addToolTips = component.getAddToolTips().isSelected();
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
