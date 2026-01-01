package com.kagof.intellij.plugins.pokeprogress.model;

public enum RegionalVariant {
    ALOLAN(Generation.VII),
    GALARIAN(Generation.VIII),
    HISUIAN(Generation.VIII),
    PALDEAN(Generation.IX);

    private final Generation generation;

    public Generation getGeneration() {
        return generation;
    }

    RegionalVariant(final Generation generation) {
        this.generation = generation;
    }
}
