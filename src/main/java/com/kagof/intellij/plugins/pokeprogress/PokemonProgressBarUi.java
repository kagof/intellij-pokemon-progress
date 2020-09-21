package com.kagof.intellij.plugins.pokeprogress;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.apache.commons.lang.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

public class PokemonProgressBarUi extends BasicProgressBarUI {
    private static final float ONE_HALF = 0.5f;
    private final Pokemon pokemon;

    private volatile int pos = 0;
    private volatile int velocity = 1;

    public PokemonProgressBarUi(final Pokemon pokemon) {
        super();
        this.pokemon = pokemon;
    }

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        c.setBorder(JBUI.Borders.empty().asUIResource());
        return new PokemonProgressBarUi(Pokemon.randomPokemon());
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return availableLength;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(20));
    }

    @Override
    protected void paintIndeterminate(final Graphics g, final JComponent c) {
        paint(g, c, false);
        updatePosition();
    }

    @Override
    protected void paintDeterminate(final Graphics g, final JComponent c) {
        resetPositionAndVelocity();
        paint(g, c, true);
    }

    private void paint(final Graphics g, final JComponent c, final boolean determinate) {
        if (isUnsupported(g, c)) {
            if (determinate) {
                super.paintDeterminate(g, c);
            } else {
                super.paintIndeterminate(g, c);
            }
            return;
        }
        setToolTipText();

        final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
        Graphics2D graphics2D = (Graphics2D) g;

        Insets border = progressBar.getInsets(); // area for border
        int width = progressBar.getWidth();
        int height = progressBar.getPreferredSize().height;

        if (!isEven(c.getHeight() - height)) {
            height++;
        }

        int barRectWidth = width - (border.right + border.left);
        int barRectHeight = height - (border.top + border.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }


        int amountFull;
        if (Pokemon.DEBUGGING) {
            amountFull = barRectWidth / 2;
        } else {
            amountFull = determinate ? getAmountFull(border, barRectWidth, barRectHeight) : pos;
        }

        Container parent = c.getParent();
        Color background = parent != null ? parent.getBackground() : UIUtil.getPanelBackground();

        graphics2D.setColor(background);
        if (c.isOpaque()) {
            graphics2D.fillRect(0, 0, width, height);
        }

        final RoundRectangle2D rectangle = getRoundRectangle(width, height);

        drawTypePaint(height, amountFull, graphics2D, rectangle);
        drawPokemonIcon(amountFull, graphics2D, rectangle);
        drawBorder(rectangle, graphics2D);
        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            graphics2D.translate(0, -(c.getHeight() - height) / 2);
            paintString(graphics2D,
                border.left,
                border.top,
                barRectWidth,
                barRectHeight,
                amountFull,
                border);
        }

        config.restore();
    }

    @NotNull
    private RoundRectangle2D getRoundRectangle(final int width, final int height) {
        final float arcLength = JBUIScale.scale(9f);
        final float offset = JBUIScale.scale(2f);

        return new RoundRectangle2D.Float(JBUIScale.scale(1f),
            JBUIScale.scale(1f),
            width - offset,
            height - offset,
            arcLength,
            arcLength);
    }

    private void drawTypePaint(final int height, final int progress, final Graphics2D graphics2D,
        final RoundRectangle2D rectangle) {
        final Paint paint = graphics2D.getPaint();
        final Shape clip = graphics2D.getClip();

        graphics2D.setPaint(getTypePaint(pokemon, height));
        graphics2D.setClip(velocity >= 0 ? new Rectangle(progress, height)
            : new Rectangle(progress, 0, progressBar.getWidth(), height));
        graphics2D.fill(rectangle);

        graphics2D.setPaint(paint);
        graphics2D.setClip(clip);
    }

    private void setToolTipText() {
        if (StringUtil.isEmptyOrSpaces(progressBar.getToolTipText())) {
            progressBar.setToolTipText(pokemon.getNameWithNumber());
        }
    }

    private void drawBorder(final RoundRectangle2D rectangle, final Graphics2D graphics2D) {
        final Color color = graphics2D.getColor();
        final Stroke stroke = graphics2D.getStroke();

        graphics2D.setColor(progressBar.getForeground());
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.draw(rectangle);

        graphics2D.setColor(color);
        graphics2D.setStroke(stroke);
    }

    private void drawPokemonIcon(final int amountFull, final Graphics2D graphics2D, final Shape clip) {
        final Shape previousClip = graphics2D.getClip();

        graphics2D.setClip(clip);
        final Icon icon = velocity >= 0 ? pokemon.getIcon() : pokemon.getIconR();
        icon.paintIcon(progressBar,
            graphics2D,
            amountFull + (velocity >= 0 ? JBUI.scale(pokemon.getXShift())
                : JBUI.scale(-icon.getIconWidth() - pokemon.getXShift())),
            JBUI.scale(pokemon.getYShift()));

        graphics2D.setClip(previousClip);
    }

    private boolean isUnsupported(final Graphics g, final JComponent c) {
        return !(g instanceof Graphics2D) || progressBar.getOrientation() != SwingConstants.HORIZONTAL
            || !c.getComponentOrientation().isLeftToRight();
    }

    private void updatePosition() {
        int v = velocity;
        int p = pos;
        if (velocity < 0) {
            if (pos <= 0) {
                velocity = 1;
                pos = 0;
            } else {
                pos = p + JBUI.scale(velocity);
                velocity = v - 1;
            }
        } else if (velocity > 0) {
            if (pos >= progressBar.getWidth()) {
                velocity = -1;
                pos = progressBar.getWidth();
            } else {
                pos = p + JBUI.scale(velocity);
                velocity = v + 1;
            }
        }
    }

    private void resetPositionAndVelocity() {
        velocity = 1;
        pos = 0;
    }

    private static Paint getTypePaint(final Pokemon pokemon, final int height) {
        final List<PokemonType> types = pokemon.getTypes();
        final int numColors = types.size();
        if (numColors == 1) {
            return getPaintSingleType(types.get(0), height);
        }

        final float numColorsReciprocal = 1f / (numColors - 1);
        return new LinearGradientPaint(0, JBUIScale.scale(2f), 0, (float) height - JBUIScale.scale(2f),
            ArrayUtils.toPrimitive(
                IntStream.range(0, numColors).mapToObj(i -> numColorsReciprocal * i).toArray(Float[]::new)),
            types.stream().map(PokemonType::getColor).collect(Collectors.toList()).toArray(new Color[] {}));
    }

    private static Paint getPaintSingleType(final PokemonType type, final int height) {
        return new LinearGradientPaint(0, JBUIScale.scale(2f), 0, (float) height - JBUIScale.scale(2f),
            new float[] {0f, ONE_HALF, 1f},
            new Color[] {type.getColorLight(), type.getColor(), type.getColorDark()});
    }

    private static boolean isEven(final int n) {
        return (n & 1) == 0;
    }
}
