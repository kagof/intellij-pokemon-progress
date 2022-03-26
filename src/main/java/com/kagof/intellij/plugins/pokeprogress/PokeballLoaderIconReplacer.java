package com.kagof.intellij.plugins.pokeprogress;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.ui.AnimatedIcon;

import icons.PokeIcons;

public class PokeballLoaderIconReplacer {
    private static List<Icon> originalIcons = null;
    private static AnimatedIcon originalInstance = null;
    private static List<Icon> pokeballIcons = null;
    private static AnimatedIcon.Frame[] originalFrames = null;

    private static boolean replaced = false;
    private static boolean reflectionFailed = false;

    @SuppressWarnings("unchecked")
    public static void updateSpinner(final boolean usePokeball) {
        if (reflectionFailed || usePokeball == replaced) {
            return;
        }
        try {
            final Class<AnimatedIcon.Default> defaultClass = AnimatedIcon.Default.class;
            final Field iconsField = defaultClass.getDeclaredField("ICONS");
            final Field instanceField = defaultClass.getDeclaredField("INSTANCE");
            Field defaultFramesField = null;
            if (ideaVersionOver2022()) {
                defaultFramesField = defaultClass.getDeclaredField("DEFAULT_FRAMES");
            }
            makeFieldNonFinal(iconsField, instanceField, defaultFramesField);
            iconsField.setAccessible(true);
            instanceField.setAccessible(true);
            if (originalIcons == null) {
                originalIcons = (List<Icon>) iconsField.get(null);
            }
            if (originalInstance == null) {
                originalInstance = (AnimatedIcon) instanceField.get(null);
            }
            iconsField.set(null, usePokeball ? getPokeballIcons() : originalIcons);
            instanceField.set(null, usePokeball ? new AnimatedIcon.Default() : originalInstance);
            if (ideaVersionOver2022()) {
                Objects.requireNonNull(defaultFramesField).setAccessible(true);
                if (originalFrames == null) {
                    originalFrames = (AnimatedIcon.Frame[]) defaultFramesField.get(null);
                }
                defaultFramesField.set(null,
                    usePokeball ? getFrames(125, getPokeballIcons().toArray(Icon[]::new)) : originalFrames);
            }
            replaced = usePokeball;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            reflectionFailed = true;
            e.printStackTrace();
        }
    }

    private static boolean ideaVersionOver2022() {
        return ApplicationInfo.getInstance().getMajorVersion().compareTo("2022") >= 0;
    }

    private static void makeFieldNonFinal(final Field... fields) throws NoSuchFieldException, IllegalAccessException {
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        for (final Field field : fields) {
            if (field != null) {
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }
        }
    }

    private static List<Icon> getPokeballIcons() {
        if (pokeballIcons == null) {
            pokeballIcons = PokeIcons.getPokeballIcons();
        }
        return pokeballIcons;
    }

    private static AnimatedIcon.Frame[] getFrames(int delay, Icon @NotNull... icons) {
        int length = icons.length;
        assert length > 0 : "empty array";
        AnimatedIcon.Frame[] frames = new AnimatedIcon.Frame[length];
        for (int i = 0; i < length; i++) {
            Icon icon = icons[i];
            assert icon != null : "null icon";
            frames[i] = new AnimatedIcon.Frame() {
                @NotNull
                @Override
                public Icon getIcon() {
                    return icon;
                }

                @Override
                public int getDelay() {
                    return delay;
                }
            };
        }
        return frames;
    }
}
