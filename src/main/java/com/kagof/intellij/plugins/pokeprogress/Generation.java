package com.kagof.intellij.plugins.pokeprogress;

public enum Generation {
    I(1),
    II(152),
    III(252),
    IV(387),
    V(494),
    VI(650),
    VII(722),
    VIII(810);

    private final int start;

    Generation(final int start) {
        this.start = start;
    }

    public static Generation getGeneration(final int number) {
        Generation gen = I;
        if (number <= 0) {
            return gen;
        }
        for (int i = 0; i < Generation.values().length; ++i) {
            gen = Generation.values()[i];
            if (i == Generation.values().length - 1) {
                break;
            }
            if (gen.start <= number && Generation.values()[i + 1].start > number) {
                break;
            }
        }
        return gen;
    }
}
