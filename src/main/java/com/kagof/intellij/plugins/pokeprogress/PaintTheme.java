package com.kagof.intellij.plugins.pokeprogress;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.ArrayUtils;

import com.intellij.ui.scale.JBUIScale;

public abstract class PaintTheme {
    private final String id;
    private final String title;

    private PaintTheme(final String id, final String title) {
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

    public static final class Smooth extends PaintTheme {

        public Smooth(final String id, final String name) {
            super(id, name);
        }

        @Override
        public Paint getPaint(final List<PokemonType> types, final int startY, final int height) {
            if (types.size() == 1) {
                return getPaintSingleType(types.get(0), startY, height);
            } else {
                return getPaintMultiType(types, startY, height);
            }
        }

        private static final float ONE_HALF = 0.5f;

        private static Paint getPaintSingleType(final PokemonType type, final int startY, final int height) {
            return new LinearGradientPaint(0,
                (float) startY + JBUIScale.scale(2f),
                0,
                (float) startY + (float) height - JBUIScale.scale(2f),
                new float[]{ 0f, ONE_HALF, 1f },
                new Color[]{ type.getColorLight(), type.getColor(), type.getColorDark() });
        }

        private static Paint getPaintMultiType(final List<PokemonType> types, final int startY, final int height) {
            final int numColors = types.size();
            final float numColorsReciprocal = 1f / (numColors - 1);
            return new LinearGradientPaint(0,
                (float) startY + JBUIScale.scale(2f),
                0,
                (float) startY + (float) height - JBUIScale.scale(2f),
                ArrayUtils.toPrimitive(
                    IntStream.range(0, numColors).mapToObj(i -> numColorsReciprocal * i).toArray(Float[]::new)),
                types.stream().map(PokemonType::getColor).collect(Collectors.toList()).toArray(new Color[0]));
        }
    }

    public static final class Flat extends PaintTheme {

        private final Function<PokemonType, Color> getColorFromType;

        public Flat(final String id, final String title, final Function<PokemonType, Color> getColorFromType) {
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
}
