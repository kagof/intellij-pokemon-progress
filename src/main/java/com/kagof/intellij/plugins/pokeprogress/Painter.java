package com.kagof.intellij.plugins.pokeprogress;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.ArrayUtils;

import com.intellij.ui.JBColor;
import com.intellij.ui.scale.JBUIScale;

public final class Painter {
    private static final float ONE_HALF = 0.5f;

    private Painter() {
    }

    public static Paint getTransparencyPaint(final Color backgroundColor, final int width, final boolean movingRight) {
        final JBColor transparent = new JBColor(new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        return new LinearGradientPaint(0, JBUIScale.scale(2f), width, JBUIScale.scale(2f),
            new float[] {0, 1}, new Color[] {movingRight ? backgroundColor : transparent,
            movingRight ? transparent : backgroundColor});
    }

    public static Paint getTypePaint(final Pokemon pokemon, final int height) {
        return getTypePaint(pokemon, 0, height);
    }

    public static Paint getTypePaint(final Pokemon pokemon, final int startY, final int height) {
        final List<PokemonType> types = pokemon.getTypes();
        final int numColors = types.size();
        if (numColors == 1) {
            return getPaintSingleType(types.get(0), startY, height);
        }

        final float numColorsReciprocal = 1f / (numColors - 1);
        return new LinearGradientPaint(0,
            (float) startY + JBUIScale.scale(2f),
            0,
            (float) startY + (float) height - JBUIScale.scale(2f),
            ArrayUtils.toPrimitive(
                IntStream.range(0, numColors).mapToObj(i -> numColorsReciprocal * i).toArray(Float[]::new)),
            types.stream().map(PokemonType::getColor).collect(Collectors.toList()).toArray(new Color[] {}));
    }

    private static Paint getPaintSingleType(final PokemonType type, final int startY, final int height) {
        return new LinearGradientPaint(0,
            (float) startY + JBUIScale.scale(2f),
            0,
            (float) startY + (float) height - JBUIScale.scale(2f),
            new float[] {0f, ONE_HALF, 1f},
            new Color[] {type.getColorLight(), type.getColor(), type.getColorDark()});
    }
}
