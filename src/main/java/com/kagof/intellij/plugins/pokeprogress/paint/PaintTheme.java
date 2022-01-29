package com.kagof.intellij.plugins.pokeprogress.paint;

import java.awt.Paint;
import java.util.List;

import com.kagof.intellij.plugins.pokeprogress.model.PokemonType;

public abstract class PaintTheme {
    private final String id;
    private final String title;

    protected PaintTheme(final String id, final String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }

    public abstract Paint getPaint(final List<PokemonType> types, final int startY, final int height);
}
