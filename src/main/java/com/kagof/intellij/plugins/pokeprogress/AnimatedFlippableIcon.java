package com.kagof.intellij.plugins.pokeprogress;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An icon implementation that is flippable & animatable. It will notify any {@link ImageObserver}s of frame updates.
 */
public class AnimatedFlippableIcon implements FlippableIcon {

    private final Frame[] frames;
    private int i;
    private Timer timer;
    private transient Set<Component> observers = new HashSet<>();

    public AnimatedFlippableIcon() {
        super();
        frames = new Frame[] {};
        i = 0;
    }

    public AnimatedFlippableIcon(final int delay, final FlippableIcon[] icons) {
        if (delay <= 0) {
            throw new IllegalArgumentException("delay must be positive");
        }
        i = 0;
        if (icons == null || icons.length == 0) {
            frames = new Frame[] {};
            return;
        }
        frames = new Frame[icons.length];
        for (int i = 0; i < icons.length; ++i) {
            final FlippableIcon icon = icons[i];
            if (icon == null) {
                throw new NullPointerException("icons[" + i + "]");
            }
            frames[i] = new Frame(icon, delay);
        }
    }

    private AnimatedFlippableIcon(final Frame[] frames,
        final int i,
        final Set<Component> observers) {
        this.frames = Arrays.copyOf(frames, frames.length);
        this.i = i;
        this.observers = new HashSet<>(observers);
    }

    public boolean addObserver(final Component observer) {
        if (observer == null) {
            return false;
        }
        return observers.add(observer);
    }

    public boolean removeObserver(final Component observer) {
        if (observer == null) {
            return false;
        }
        return observers.remove(observer);
    }

    public void start() {
        if (timer != null || frames.length <= 1) {
            return;
        }
        timer = new Timer();
        timer.schedule(new Animator(), frames[i].delay);
    }

    public void stop() {
        if (timer == null) {
            return;
        }
        timer.cancel();
        timer = null;
    }

    public void reset() {
        i = 0;
    }

    @Override
    public synchronized void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        if (frames.length == 0) {
            return;
        }
        frames[i].icon.paintIcon(c, g, x, y);
        start();
        addObserver(c);
    }

    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }

    public int getFrameCount() {
        return frames.length;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AnimatedFlippableIcon that = (AnimatedFlippableIcon) o;
        return i == that.i && Arrays.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(i);
        result = 31 * result + Arrays.hashCode(frames);
        return result;
    }

    private void nextFrame() {
        i = (i + 1) % frames.length;
    }

    @Override
    public AnimatedFlippableIcon flipHorizontal() {
        final AnimatedFlippableIcon flipped = new AnimatedFlippableIcon(
            Arrays.stream(frames).map(Frame::flipHorizontal).toArray(Frame[]::new),
            i,
            observers);
        if (timer != null) {
            flipped.start();
        }
        return flipped;
    }

    @Override
    public FlippableIcon flipVertical() {
        final AnimatedFlippableIcon flipped = new AnimatedFlippableIcon(
            Arrays.stream(frames).map(Frame::flipVertical).toArray(Frame[]::new),
            i,
            observers);
        if (timer != null) {
            flipped.start();
        }
        return flipped;
    }

    private static class Frame {
        private final FlippableIcon icon;
        private final int delay;

        public Frame(final FlippableIcon icon, final int delay) {
            this.icon = icon;
            this.delay = delay;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Frame frame = (Frame) o;
            return delay == frame.delay && icon.equals(frame.icon);
        }

        @Override
        public int hashCode() {
            return Objects.hash(icon, delay);
        }

        public Frame flipHorizontal() {
            return new Frame(icon.flipHorizontal(), delay);
        }

        public Frame flipVertical() {
            return new Frame(icon.flipVertical(), delay);
        }
    }

    private class Animator extends TimerTask {
        @Override
        public void run() {
            nextFrame();
            observers.forEach(observer -> {
                final FlippableIcon icon = frames[i].icon;
                if (icon.getIconHeight() == 0 || icon.getIconWidth() == 0) {
                    return;
                }
                BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
                Graphics g = img.createGraphics();
                icon.paintIcon(null, g, 0, 0);
                g.dispose();
                observer.imageUpdate(img, ImageObserver.FRAMEBITS, 0, 0, getIconWidth(), getIconHeight());
            });

            // If all observers are not showing, then stop the animation.
            if (observers.stream().anyMatch(Component::isShowing)) {
                timer.schedule(new Animator(), frames[i].delay);
            } else {
                stop();
            }
        }
    }

}
