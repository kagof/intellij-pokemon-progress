package com.kagof.intellij.plugins.pokeprogress;

import java.util.Objects;

import javax.swing.UIManager;

import org.jetbrains.annotations.NotNull;

import com.intellij.ide.plugins.DynamicPluginListener;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;

public class PokemonProgressListener implements LafManagerListener, DynamicPluginListener {
    private static final String PROGRESS_BAR_UI_KEY = "ProgressBarUI";
    private static final String POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME = PokemonProgressBarUi.class.getName();
    private volatile static Object previousProgressBar = null;

    public PokemonProgressListener() {
        updateProgressBarUi();
    }

    @Override
    public void lookAndFeelChanged(@NotNull final LafManager lafManager) {
        updateProgressBarUi();
    }

    @Override
    public void pluginLoaded(@NotNull final IdeaPluginDescriptor pluginDescriptor) {
        updateProgressBarUi();
    }

    @Override
    public void beforePluginUnload(@NotNull final IdeaPluginDescriptor pluginDescriptor, final boolean isUpdate) {
        resetProgressBarUi();
    }

    static void updateProgressBarUi() {
        final Object prev = UIManager.get(PROGRESS_BAR_UI_KEY);
        if (!Objects.equals(POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME, prev)) {
            previousProgressBar = prev;
        }
        UIManager.put(PROGRESS_BAR_UI_KEY, POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME);
        UIManager.getDefaults().put(PokemonProgressBarUi.class.getName(), PokemonProgressBarUi.class);
    }

    static void resetProgressBarUi() {
        UIManager.put(PROGRESS_BAR_UI_KEY, previousProgressBar);
    }
}
