package com.kagof.intellij.plugins.pokeprogress;

import java.util.Arrays;

import com.google.common.collect.ImmutableMap;
import com.kagof.intellij.plugins.pokeprogress.PaintTheme.Flat;
import com.kagof.intellij.plugins.pokeprogress.PaintTheme.Smooth;

import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static java.util.function.Function.identity;

public final class PaintThemes {
    private static final ImmutableMap<String, PaintTheme> allThemes;
    private static final PaintTheme defaultTheme;

    static {
        defaultTheme = new Smooth("smooth", "Smooth");

        final PaintTheme[] themes = {
            defaultTheme,
            new Flat("flat", "Flat", PokemonType::getColor),
            new Flat("flat_light", "Flat (Light)", PokemonType::getColorLight),
            new Flat("flat_dark", "Flat (Dark)", PokemonType::getColorDark),
        };

        allThemes = Arrays.stream(themes).collect(toImmutableMap(PaintTheme::getId, identity()));
    }

    public static PaintTheme[] getAll() {
        return allThemes.values().toArray(new PaintTheme[0]); // ImmutableMap preserves insertion order
    }

    public static PaintTheme getByIdOrDefault(final String id) {
        return allThemes.getOrDefault(id, defaultTheme);
    }

    public static PaintTheme getDefaultTheme() {
        return defaultTheme;
    }

    private PaintThemes() {
    }
}
