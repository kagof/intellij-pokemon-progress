package com.kagof.intellij.plugins.pokeprogress.theme;

import java.awt.Color;

public record TypeColor(Color colorLight, Color color, Color colorDark) {
    public TypeColor(final String colorLight, final String color, final String colorDark) {
        this(Color.decode(colorLight), Color.decode(color), Color.decode(colorDark));
    }
}
