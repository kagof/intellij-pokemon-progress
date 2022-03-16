package com.kagof.intellij.plugins.pokeprogress.theme;

import java.util.LinkedHashMap;

import com.kagof.intellij.plugins.pokeprogress.model.PokemonType;

public class ColorScheme {
    private final String id;
    private final String name;
    private final LinkedHashMap<PokemonType, TypeColor> values;

    public ColorScheme(final String id, final String name, final LinkedHashMap<PokemonType, TypeColor> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public TypeColor get(final PokemonType type) {
        return values.getOrDefault(type, values.values().stream().findFirst().orElse(null));
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
