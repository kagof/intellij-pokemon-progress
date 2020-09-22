package com.kagof.intellij.plugins.pokeprogress.configuration;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;

public class PokemonProgressConfigurable implements Configurable {
    private PokemonProgressConfigurationComponent component;

    @Override
    @NlsContexts.ConfigurableName
    public  String getDisplayName() {
        return "Pokémon Progress";
    }

    @Override
    public @Nullable JComponent createComponent() {
        component = new PokemonProgressConfigurationComponent();
        return component.getPanel();
    }

    @Override
    public boolean isModified() {
        PokemonProgressState state = PokemonProgressState.getInstance();
        return !state.pokemonNumbersEnabled.equals(component.getEnabledNumberMap());
    }

    @Override
    public void apply() throws ConfigurationException {
        PokemonProgressState state = PokemonProgressState.getInstance();
        state.pokemonNumbersEnabled = component.getEnabledNumberMap();
    }

    @Override
    public void reset() {
        PokemonProgressState state = PokemonProgressState.getInstance();
        component.updateUi(state);
    }

    @Override
    public void disposeUIResources() {
        component = null;
    }
}
