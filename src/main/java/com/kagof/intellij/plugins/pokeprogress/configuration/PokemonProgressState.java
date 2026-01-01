package com.kagof.intellij.plugins.pokeprogress.configuration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.kagof.intellij.plugins.pokeprogress.PokeballLoaderIconReplacer;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@State(
    name = "com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState",
    storages = {@Storage("PokemonProgress.xml")}
)
public class PokemonProgressState implements PersistentStateComponent<PokemonProgressState> {
    public String version;

    public float initialVelocity = 1.0f;
    public float acceleration = 0.4f;

    // Named poorly; this is actually Pokemon IDs enabled
    public Map<String, Boolean> pokemonNumbersEnabled = Pokemon.DEFAULT_POKEMON.keySet().stream()
        .collect(Collectors.toMap(Function.identity(), p -> true));
    public String theme;
    public boolean drawSprites = true;
    public boolean addToolTips = true;
    public boolean addIconToToolTips = true;
    public boolean transparencyOnIndeterminate = true;
    public boolean transparencyOnDeterminate = false;
    public String colorScheme;
    private boolean replaceLoaderIcon = true;
    public boolean showUpdateNotification = true;

    public boolean restrictMaximumHeight = false;
    public int maximumHeight = 20;
    public boolean restrictMinimumHeight = false;
    public int minimumHeight = 20;

    public void setReplaceLoaderIcon(final boolean updated) {
        this.replaceLoaderIcon = updated;
        PokeballLoaderIconReplacer.updateSpinner(updated);
    }

    public boolean isReplaceLoaderIcon() {
        return replaceLoaderIcon;
    }

    public void setHeightLimits(final int newMaxHeight, final int newMinHeight) {
        if (newMinHeight > newMaxHeight) {
            minimumHeight = newMaxHeight;
            maximumHeight = newMaxHeight;
        } else {
            minimumHeight = newMinHeight;
            maximumHeight = newMaxHeight;
        }
    }

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
    }
}
