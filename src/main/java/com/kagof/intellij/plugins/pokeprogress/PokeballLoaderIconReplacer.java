package com.kagof.intellij.plugins.pokeprogress;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.swing.Icon;

import com.intellij.ui.AnimatedIcon;

import icons.PokeIcons;

public class PokeballLoaderIconReplacer {
    private static List<Icon> originalIcons = null;
    private static AnimatedIcon originalInstance = null;
    private static List<Icon> pokeballIcons = null;

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
            makeFieldNonFinal(iconsField, instanceField);
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
            replaced = usePokeball;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            reflectionFailed = true;
            e.printStackTrace();
        }
    }

    private static void makeFieldNonFinal(final Field... fields) throws NoSuchFieldException, IllegalAccessException {
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        for (final Field field : fields) {
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        }
    }

    private static List<Icon> getPokeballIcons() {
        if (pokeballIcons == null) {
            pokeballIcons = PokeIcons.getPokeballIcons();
        }
        return pokeballIcons;
    }
}
