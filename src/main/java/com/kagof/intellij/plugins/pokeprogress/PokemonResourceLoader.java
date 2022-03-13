package com.kagof.intellij.plugins.pokeprogress;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import javax.swing.Icon;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;

public final class PokemonResourceLoader {
    private static final String SPRITE_RESOURCE_PATH = "com/kagof/intellij/plugins/pokeprogress/sprites/";

    private static final Cache<String, FlippableIcon> cache = CacheBuilder
        .newBuilder()
        .maximumSize(100L)
        .build();

    private static final Cache<Pokemon, FlippableIcon> pokeCache = CacheBuilder
        .newBuilder()
        .maximumSize(100L)
        .build();

    private PokemonResourceLoader() {
    }

    public static Icon getIcon(final Pokemon pokemon) {
        return getIconInternal(pokemon);
    }

    public static Icon getReversedIcon(final Pokemon pokemon) {
        return getIconInternal(getIconPath(pokemon)).flipHorizontal();
    }

    public static String getIconPath(final Pokemon pokemon) {
        return SPRITE_RESOURCE_PATH + pokemon.getName().replace(' ', '_') + "_1.svg";
    }

    public static String getIconPath(final Pokemon pokemon, final int frame) {
        return SPRITE_RESOURCE_PATH + pokemon.getName().replace(' ', '_') + "_" + frame + ".svg";
    }

    public static String getReversedIconPath(final Pokemon pokemon) {
        return SPRITE_RESOURCE_PATH + pokemon.getName().replace(' ', '_') + "_r.gif";
    }

    public static Optional<URL> getResource(final String resourceName) {
        return Optional
            .ofNullable(PokemonResourceLoader.class.getClassLoader()
                .getResource(resourceName))
            .or(() -> Optional.ofNullable(PokemonResourceLoader.class.getClassLoader()
                .getResource(resourceName.startsWith("/") ? resourceName.replaceFirst("/", "") : "/" + resourceName)));
    }

    public static Optional<InputStream> getResourceAsStream(final String resourceName) {
        return Optional
            .ofNullable(PokemonResourceLoader.class.getClassLoader()
                .getResourceAsStream(resourceName))
            .or(() -> Optional.ofNullable(PokemonResourceLoader.class.getClassLoader()
                .getResourceAsStream(
                    resourceName.startsWith("/") ? resourceName.replaceFirst("/", "") : "/" + resourceName)));
    }

    private static FlippableIcon getIconInternal(final Pokemon pokemon) {
        try {
            return pokeCache.get(pokemon, () -> {
                if (pokemon.getNumberOfFrames() == 1) {
                    return getIconInternal(getIconPath(pokemon, 1));
                }
                return new AnimatedFlippableIcon(500, IntStream.range(1, pokemon.getNumberOfFrames() + 1)
                    .mapToObj(n -> getIconPath(pokemon, n))
                    .map(PokemonResourceLoader::getIconInternal)
                    .toArray(FlippableIcon[]::new));
            });
        } catch (ExecutionException e) {
            return new SvgIcon();
        }
    }

    private static FlippableIcon getIconInternal(final String resourceName) {
        try {
            final FlippableIcon icon = cache.get(resourceName,
                () -> getResource(resourceName)
                    .map(SvgIcon::new)
                    .orElseGet(SvgIcon::new));
            return icon;
        } catch (final ExecutionException e) {
            return new SvgIcon();
        }
    }
}
