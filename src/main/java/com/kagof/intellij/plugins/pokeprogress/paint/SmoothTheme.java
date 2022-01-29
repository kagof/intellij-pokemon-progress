package com.kagof.intellij.plugins.pokeprogress.paint;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.ArrayUtils;

import com.intellij.ui.scale.JBUIScale;
import com.kagof.intellij.plugins.pokeprogress.model.PokemonType;

public class SmoothTheme extends PaintTheme {

    public SmoothTheme(final String id, final String name) {
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
            new float[] {0f, ONE_HALF, 1f},
            new Color[] {type.getColorLight(), type.getColor(), type.getColorDark()});
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
