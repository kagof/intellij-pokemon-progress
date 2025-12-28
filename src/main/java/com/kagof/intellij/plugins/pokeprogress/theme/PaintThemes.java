package com.kagof.intellij.plugins.pokeprogress.theme;

import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static java.util.function.Function.identity;

import java.util.Arrays;

import com.google.common.collect.ImmutableMap;

public final class PaintThemes {
    private static final ImmutableMap<String, PaintTheme> allThemes;
    private static final PaintTheme defaultTheme;

    static {
        defaultTheme = new SmoothTheme("smooth", "Smooth");

        final PaintTheme[] themes = {
            defaultTheme,
            new FlatTheme("flat", "Flat", TypeColor::color),
            new FlatTheme("flat_light", "Flat (Light)", TypeColor::colorLight),
            new FlatTheme("flat_dark", "Flat (Dark)", TypeColor::colorDark),
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
