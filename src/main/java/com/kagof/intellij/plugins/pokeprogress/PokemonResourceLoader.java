package com.kagof.intellij.plugins.pokeprogress;

import javax.swing.Icon;

import com.intellij.openapi.util.IconLoader;

public final class PokemonResourceLoader {
    private static final String SPRITE_RESOURCE_PATH = "/com/kagof/intellij/plugins/pokeprogress/sprites/";

    private PokemonResourceLoader() {
    }

    public static Icon getIcon(final Pokemon pokemon) {
        return IconLoader.getIcon(SPRITE_RESOURCE_PATH + pokemon.getName() + ".gif");
    }

    public static Icon getReversedIcon(final Pokemon pokemon) {
        return IconLoader.getIcon(SPRITE_RESOURCE_PATH + pokemon.getName() + "_r.gif");
    }
}
