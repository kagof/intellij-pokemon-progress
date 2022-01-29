package com.kagof.intellij.plugins.pokeprogress.paint;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;
import java.util.function.Function;

import com.kagof.intellij.plugins.pokeprogress.model.PokemonType;

public class FlatTheme extends PaintTheme {

    private final Function<PokemonType, Color> getColorFromType;

    public FlatTheme(final String id, final String title, final Function<PokemonType, Color> getColorFromType) {
        super(id, title);
        this.getColorFromType = getColorFromType;
    }

    @Override
    public Paint getPaint(final List<PokemonType> types, final int startY, final int height) {
        final int numColors = types.size();
        if (numColors == 1) {
            return getColorFromType.apply(types.get(0));
        }

        final float numColorsReciprocal = 1f / numColors;
        final float[] fractions = new float[numColors * 2];
        final Color[] colors = new Color[numColors * 2];

        for (int i = 0; i < numColors; i++) {
            fractions[i * 2] = i == 0 ? 0F : Math.nextUp(numColorsReciprocal * i);
            fractions[i * 2 + 1] = i == numColors - 1 ? 1F : Math.nextDown(numColorsReciprocal * (i + 1));
            final Color color = getColorFromType.apply(types.get(i));
            colors[i * 2] = color;
            colors[i * 2 + 1] = color;
        }

        return new LinearGradientPaint(0, startY, 0, startY + height, fractions, colors);
    }
}
