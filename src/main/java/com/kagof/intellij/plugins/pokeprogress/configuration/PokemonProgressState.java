package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.kagof.intellij.plugins.pokeprogress.Pokemon;

@State(
    name = "com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState",
    storages = {@Storage("PokemonProgress.xml")}
)
public class PokemonProgressState implements PersistentStateComponent<PokemonProgressState> {
    private float initialVelocity = 1.0f;
    private float acceleration = 0.4f;
    private Map<String, Boolean> pokemonNumbersEnabled = Pokemon.DEFAULT_POKEMON.keySet().stream()
        .collect(Collectors.toMap(Function.identity(), p -> true));
    private boolean drawSprites = true;
    private boolean addToolTips = true;

    public static PokemonProgressState getInstance() {
        return ServiceManager.getService(PokemonProgressState.class);
    }

    @Override
    public PokemonProgressState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final PokemonProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public float getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(final float initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(final float acceleration) {
        this.acceleration = acceleration;
    }

    public Map<String, Boolean> getPokemonNumbersEnabled() {
        return new HashMap<>(pokemonNumbersEnabled);
    }

    public void setPokemonNumbersEnabled(final Map<String, Boolean> pokemonNumbersEnabled) {
        this.pokemonNumbersEnabled = pokemonNumbersEnabled;
    }

    public boolean isDrawSprites() {
        return drawSprites;
    }

    public void setDrawSprites(final boolean drawSprites) {
        this.drawSprites = drawSprites;
    }

    public boolean isAddToolTips() {
        return addToolTips;
    }

    public void setAddToolTips(final boolean addToolTips) {
        this.addToolTips = addToolTips;
    }
}
