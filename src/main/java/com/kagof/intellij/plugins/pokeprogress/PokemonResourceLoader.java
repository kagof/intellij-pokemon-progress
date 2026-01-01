package com.kagof.intellij.plugins.pokeprogress;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.swing.ImageIcon;

public final class PokemonResourceLoader {
    private static final String SPRITE_RESOURCE_PATH = "com/kagof/intellij/plugins/pokeprogress/sprites/";

    private static final Cache<String, ImageIcon> cache = CacheBuilder
        .newBuilder()
        .maximumSize(100L)
        .build();

    private PokemonResourceLoader() {
    }

    public static ImageIcon getIcon(final Pokemon pokemon) {
        return getIconInternal(getIconPath(pokemon));
    }

    public static ImageIcon getReversedIcon(final Pokemon pokemon) {
        return getIconInternal(getReversedIconPath(pokemon));
    }

    public static String getIconPath(final Pokemon pokemon) {
        return SPRITE_RESOURCE_PATH + pokemon.getName().replace(' ', '_') + ".gif";
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

    private static ImageIcon getIconInternal(final String resourceName) {
        try {
            return cache.get(resourceName,
                () -> getResource(resourceName)
                    .map(ImageIcon::new)
                    .orElseGet(ImageIcon::new));
        } catch (final ExecutionException e) {
            return new ImageIcon();
        }
    }
}
