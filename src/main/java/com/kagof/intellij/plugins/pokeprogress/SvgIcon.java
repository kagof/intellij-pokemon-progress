package com.kagof.intellij.plugins.pokeprogress;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Objects;

import com.intellij.util.ImageLoader;

public class SvgIcon implements FlippableIcon {
    final URL svgUrl;
    final boolean flipHorizontal;
    final boolean flipVertical;
    Integer width = null;
    Integer height = null;

    public SvgIcon(URL url, final boolean flipHorizontal, final boolean flipVertical) {
        this(url, flipHorizontal, flipVertical, null, null);
    }

    private SvgIcon(URL url, final boolean flipHorizontal, final boolean flipVertical, final Integer width,
        final Integer height) {
        this.svgUrl = url;
        this.flipHorizontal = flipHorizontal;
        this.flipVertical = flipVertical;
        this.width = width;
        this.height = height;
    }

    public SvgIcon(URL url, final boolean flipHorizontal) {
        this(url, flipHorizontal, false);
    }

    public SvgIcon(URL url) {
        this(url, false, false);
    }

    public SvgIcon() {
        this(null, false, false);
    }

    @Override
    public FlippableIcon flipHorizontal() {
        return new SvgIcon(svgUrl, !flipHorizontal, flipVertical, width, height);
    }

    @Override
    public FlippableIcon flipVertical() {
        return new SvgIcon(svgUrl, flipHorizontal, !flipVertical, width, height);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SvgIcon svgIcon = (SvgIcon) o;
        return flipHorizontal == svgIcon.flipHorizontal
            && flipVertical == svgIcon.flipVertical
            && Objects.equals(svgUrl, svgIcon.svgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svgUrl, flipHorizontal, flipVertical);
    }

    @Override
    public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        final Image image = getImage();
        if (image == null) {
            return;
        }
        final Graphics2D g2 = (Graphics2D) g;
        final AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
        if (flipHorizontal || flipVertical) {
            transform.translate(flipHorizontal ? image.getWidth(null) : 0, flipVertical ? image.getHeight(null) : 0);
            transform.scale(flipHorizontal ? -1 : 1, flipVertical ? -1 : 1);
        }
        g2.drawImage(image, transform, c);
    }

    @Override
    public int getIconWidth() {
        if (svgUrl == null) {
            return 0;
        }
        if (width == null) {
            getImage();
        }
        return width;
    }

    @Override
    public int getIconHeight() {
        if (svgUrl == null) {
            return 0;
        }
        if (height == null) {
            getImage();
        }
        return height;
    }

    private Image getImage() {
        if (svgUrl == null) {
            return null;
        } else {
            final Image image = ImageLoader.loadFromUrl(svgUrl);
            if (height == null) {
                height = image.getHeight(null);
            }
            if (width == null) {
                width = image.getWidth(null);
            }
            return image;
        }
    }
}
