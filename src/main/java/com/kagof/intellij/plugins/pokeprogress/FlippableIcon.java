package com.kagof.intellij.plugins.pokeprogress;

import javax.swing.Icon;

public interface FlippableIcon extends Icon {
    FlippableIcon flipHorizontal();

    FlippableIcon flipVertical();
}
