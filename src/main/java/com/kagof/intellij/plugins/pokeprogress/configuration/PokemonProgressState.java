package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;

@State(
    name = "com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState",
    storages = {@Storage("PokemonProgress.xml")}
)
public class PokemonProgressState implements PersistentStateComponent<PokemonProgressState> {
    public float initialVelocity = 1.0f;
    public float acceleration = 0.4f;
    public Map<String, Boolean> pokemonNumbersEnabled = Pokemon.DEFAULT_POKEMON.keySet().stream()
        .collect(Collectors.toMap(Function.identity(), p -> true));
    public String theme;
    public boolean drawSprites = true;
    public boolean addToolTips = true;
    public boolean transparencyOnIndeterminate = true;
    public boolean transparencyOnDeterminate = false;

    public static PokemonProgressState getInstance() {
        return ApplicationManager.getApplication().getService(PokemonProgressState.class);
    }

    @Override
    public PokemonProgressState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final PokemonProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
        System.out.print("loading state: ");
        System.out.println(this);
    }
}
