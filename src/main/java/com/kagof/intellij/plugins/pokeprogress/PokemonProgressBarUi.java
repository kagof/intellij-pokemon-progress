package com.kagof.intellij.plugins.pokeprogress;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.JBColor;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorScheme;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorSchemes;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintTheme;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintThemes;

public class PokemonProgressBarUi extends BasicProgressBarUI {
    private static final String DEBUGGING_ENV_VAR = "POKEMON_PROGRESS_DEBUG";

    private final Pokemon pokemon;
    private final ImageIcon iconForward;
    private final ImageIcon iconReversed;
    private ImageIcon iconForwardScaled = null;
    private ImageIcon iconReversedScaled = null;
    private final Supplier<Float> initialVelocity;
    private final Supplier<Float> acceleration;
    private final Supplier<PaintTheme> theme;
    private final Supplier<ColorScheme> colorScheme;
    private final Supplier<Boolean> transparencyOnIndeterminate;
    private final Supplier<Boolean> transparencyOnDeterminate;
    private final Supplier<Boolean> drawSprites;
    private final Supplier<Boolean> addToolTips;
    private final Supplier<Boolean> addIconToToolTips;
    private final Supplier<Boolean> restrictMaxHeight;
    private final Supplier<Integer> maxHeight;
    private final Supplier<Boolean> restrictMinHeight;
    private final Supplier<Integer> minHeight;

    private volatile int pos = 0;
    private volatile float velocity = 0;

    public PokemonProgressBarUi(final Pokemon pokemon) {
        this(pokemon,
            safeGetFromState(s -> s.initialVelocity, 1.0f),
            safeGetFromState(s -> s.acceleration, 0.4f),
            safeGetFromState(s -> PaintThemes.getByIdOrDefault(s.theme), PaintThemes.getDefaultTheme()),
            safeGetFromState(s -> ColorSchemes.getByIdOrDefault(s.colorScheme), ColorSchemes.getDefaultScheme()),
            safeGetFromState(s -> s.transparencyOnIndeterminate, true),
            safeGetFromState(s -> s.transparencyOnDeterminate, false),
            safeGetFromState(s -> s.drawSprites, true),
            safeGetFromState(s -> s.addToolTips, true),
            safeGetFromState(s -> s.addIconToToolTips, true),
            safeGetFromState(s -> s.restrictMaximumHeight, false),
            safeGetFromState(s -> s.maximumHeight, 20),
            safeGetFromState(s -> s.restrictMinimumHeight, false),
            safeGetFromState(s -> s.minimumHeight, 20));
    }

    public PokemonProgressBarUi(final Pokemon pokemon,
        final Supplier<Float> initialVelocity,
        final Supplier<Float> acceleration,
        final Supplier<PaintTheme> theme,
        final Supplier<ColorScheme> colorScheme,
        final Supplier<Boolean> transparencyOnIndeterminate,
        final Supplier<Boolean> transparencyOnDeterminate,
        final Supplier<Boolean> drawSprites,
        final Supplier<Boolean> addToolTips,
        final Supplier<Boolean> addIconToToolTips,
        final Supplier<Boolean> restrictMaxHeight,
        final Supplier<Integer> maxHeight,
        final Supplier<Boolean> restrictMinHeight,
        final Supplier<Integer> minHeight) {
        super();
        this.pokemon = pokemon;
        this.initialVelocity = initialVelocity;
        this.acceleration = acceleration;
        this.theme = theme;
        this.colorScheme = colorScheme;
        this.transparencyOnIndeterminate = transparencyOnIndeterminate;
        this.transparencyOnDeterminate = transparencyOnDeterminate;
        this.drawSprites = drawSprites;
        this.addToolTips = addToolTips;
        this.addIconToToolTips = addIconToToolTips;
        this.restrictMaxHeight = restrictMaxHeight;
        this.maxHeight = maxHeight;
        this.restrictMinHeight = restrictMinHeight;
        this.minHeight = minHeight;
        velocity = initialVelocity.get();

        iconForward = PokemonResourceLoader.getIcon(pokemon);
        iconReversed = PokemonResourceLoader.getReversedIcon(pokemon);
        if (restrictMaxHeight.get() || restrictMinHeight.get()) {
            computeScaledIcons();
        }
    }

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(final JComponent c) {
        c.setBorder(JBUI.Borders.empty().asUIResource());
        return new PokemonProgressBarUi(PokemonPicker.get());
    }

    @Override
    protected int getBoxLength(final int availableLength, final int otherDimension) {
        return availableLength;
    }

    @Override
    public Dimension getPreferredSize(final JComponent c) {
        int height = restrictMaxHeight.get() ? Math.min(maxHeight.get(), pokemon.getHeight())
            : pokemon.getHeight();
        height = restrictMinHeight.get() ? Math.max(minHeight.get(), height) : height;
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(height));
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

    public void computeScaledIcons() {
        iconForwardScaled = new ImageIcon(iconForward.getImage()
            .getScaledInstance(-1, scaleToHeightRestrictions(iconForward.getIconHeight()), Image.SCALE_DEFAULT));
        iconReversedScaled = new ImageIcon(iconReversed.getImage()
            .getScaledInstance(-1, scaleToHeightRestrictions(iconReversed.getIconHeight()), Image.SCALE_DEFAULT));
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
        final Graphics2D graphics2D = (Graphics2D) g;

        final Insets border = progressBar.getInsets(); // area for border
        final int width = progressBar.getWidth();
        int height = progressBar.getPreferredSize().height;

        if (!isEven(c.getHeight() - height)) {
            height++;
        }

        final int barRectWidth = width - (border.right + border.left);
        final int barRectHeight = height - (border.top + border.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }

        final int amountFull;
        if (System.getenv().containsKey(DEBUGGING_ENV_VAR)) {
            amountFull = barRectWidth / 2;
        } else {
            amountFull = determinate ? getAmountFull(border, barRectWidth, barRectHeight) : pos;
        }

        final Container parent = c.getParent();
        final Color background = parent != null ? parent.getBackground() : UIUtil.getPanelBackground();

        graphics2D.setColor(background);
        if (c.isOpaque()) {
            graphics2D.fillRect(0, 0, width, height);
        }

        final RoundRectangle2D rectangle = getRoundRectangle(width, height);

        drawTypePaint(width, height, amountFull, graphics2D, rectangle);
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

    private void drawTypePaint(final int width, final int height, final int progress, final Graphics2D graphics2D,
        final RoundRectangle2D rectangle) {
        final Paint paint = graphics2D.getPaint();
        final Shape clip = graphics2D.getClip();
        final boolean movingRight = velocity >= 0;

        graphics2D.setPaint(theme.get().getPaint(pokemon.getTypes(), colorScheme.get(), 0, height));
        graphics2D.setClip(movingRight ? new Rectangle(progress, height)
            : new Rectangle(progress, 0, progressBar.getWidth(), height));
        graphics2D.fill(rectangle);

        if ((progressBar.isIndeterminate() && transparencyOnIndeterminate.get())
            || (!progressBar.isIndeterminate() && transparencyOnDeterminate.get())) {
            graphics2D.setPaint(getTransparencyPaint(progressBar.getBackground(), width, movingRight));
            graphics2D.setClip(movingRight ? new Rectangle(progress, height)
                : new Rectangle(progress, 0, progressBar.getWidth(), height));
            graphics2D.fill(rectangle);
        }

        graphics2D.setPaint(paint);
        graphics2D.setClip(clip);
    }

    private static Paint getTransparencyPaint(final Color backgroundColor, final int width, final boolean movingRight) {
        final JBColor transparent = new JBColor(new Color(0, 0, 0, 0), new Color(0, 0, 0, 0));
        return new LinearGradientPaint(0, JBUIScale.scale(2f), width, JBUIScale.scale(2f),
            new float[] {0, 1}, new Color[] {movingRight ? backgroundColor : transparent,
                movingRight ? transparent : backgroundColor});
    }

    private void setToolTipText() {
        if (addToolTips.get()) {
            if (addIconToToolTips.get()) {
                final Optional<URL> urlOpt = PokemonResourceLoader.getResource(PokemonResourceLoader.getIconPath(pokemon));
                if (urlOpt.isPresent()) {
                    progressBar.setToolTipText("<html><body><img src=\"" + urlOpt.get() + "\"></img>" + pokemon.getNameWithNumber() + "</body></html>");
                    return;
                }
            }
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
        if (!drawSprites.get()) {
            return;
        }
        final Shape previousClip = graphics2D.getClip();

        graphics2D.setClip(clip);
        final Icon icon;
        if (restrictMaxHeight.get() || restrictMinHeight.get()) {
            icon = velocity >= 0 ? iconForwardScaled : iconReversedScaled;
        } else {
            icon = velocity >= 0 ? iconForward : iconReversed;
        }
        if (icon != null) {
            icon.paintIcon(progressBar,
                graphics2D,
                amountFull + (velocity >= 0 ? JBUI.scale(scaleToHeightRestrictions(pokemon.getXShift()))
                    : JBUI.scale(-icon.getIconWidth() - scaleToHeightRestrictions(pokemon.getXShift()))),
                JBUI.scale(scaleToHeightRestrictions(pokemon.getYShift())));
        }
        graphics2D.setClip(previousClip);
    }

    private boolean isUnsupported(final Graphics g, final JComponent c) {
        return !(g instanceof Graphics2D) || progressBar.getOrientation() != SwingConstants.HORIZONTAL
            || !c.getComponentOrientation().isLeftToRight();
    }

    private void updatePosition() {
        final float v = velocity;
        final int p = pos;
        if (velocity < 0) {
            if (pos <= 0) {
                velocity = initialVelocity.get();
                pos = 0;
            } else {
                pos = p + (int) JBUIScale.scale(velocity);
                velocity = v - acceleration.get();
            }
        } else if (velocity > 0) {
            if (pos >= progressBar.getWidth()) {
                velocity = -initialVelocity.get();
                pos = progressBar.getWidth();
            } else {
                pos = p + (int) JBUIScale.scale(velocity);
                velocity = v + acceleration.get();
            }
        }
    }

    private int scaleToHeightRestrictions(final int value) {
        if (restrictMaxHeight.get() && pokemon.getHeight() > maxHeight.get()) {
            return Math.round(((float) maxHeight.get() / pokemon.getHeight()) * value);
        }
        if (restrictMinHeight.get() && pokemon.getHeight() < minHeight.get()) {
            return Math.round(((float) minHeight.get() / pokemon.getHeight()) * value);
        }
        return value;
    }

    private void resetPositionAndVelocity() {
        velocity = 1;
        pos = 0;
    }

    private static boolean isEven(final int n) {
        return (n & 1) == 0;
    }

    private static <T> Supplier<T> safeGetFromState(final Function<PokemonProgressState, T> getter,
        final T defaultIfStateNull) {
        return () -> Optional.ofNullable(PokemonProgressState.getInstance())
            .map(getter)
            .orElse(defaultIfStateNull);
    }
}
