package com.kagof.intellij.plugins.pokeprogress;

import com.intellij.ide.plugins.DynamicPluginListener;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;
import java.util.Objects;
import java.util.Optional;
import javax.swing.UIManager;
import org.jetbrains.annotations.NotNull;

public class PokemonProgressListener implements LafManagerListener, DynamicPluginListener {
    private static final String PLUGIN_ID_STRING = "com.kagof.pokeprogress";
    private static final String PROGRESS_BAR_UI_KEY = "ProgressBarUI";
    private static final String POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME = PokemonProgressBarUi.class.getName();
    private volatile static Object previousProgressBar = null;
    private volatile static PluginId pluginId = null;
    private static boolean initialized = false;

    public PokemonProgressListener() {
        initializePlugin();
    }

    private void initializePlugin() {
        if (!initialized) {
            updateProgressBarUi();
            pluginId = PluginId.getId(PLUGIN_ID_STRING);
            initialized = true;
        }
    }

    @Override
    public void lookAndFeelChanged(@NotNull final LafManager lafManager) {
        updateProgressBarUi();
    }

    @Override
    public void pluginLoaded(@NotNull final IdeaPluginDescriptor pluginDescriptor) {
        if (Objects.equals(pluginId, pluginDescriptor.getPluginId())) {
            updateProgressBarUi();
        }
    }

    @Override
    public void beforePluginUnload(@NotNull final IdeaPluginDescriptor pluginDescriptor, final boolean isUpdate) {
        if (Objects.equals(pluginId, pluginDescriptor.getPluginId())) {
            resetProgressBarUi();
        }
    }

    static void updateProgressBarUi() {
        ApplicationManager.getApplication().invokeLater(() -> {
            final Object prev = UIManager.get(PROGRESS_BAR_UI_KEY);
            if (!Objects.equals(POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME, prev)) {
                previousProgressBar = prev;
            }
            Optional.ofNullable(PokemonProgressState.getInstance())
                .ifPresent(s -> PokeballLoaderIconReplacer.updateSpinner(s.isReplaceLoaderIcon()));
            UIManager.put(PROGRESS_BAR_UI_KEY, POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME);
            UIManager.getDefaults().put(POKEMON_PROGRESS_BAR_UI_IMPLEMENTATION_NAME, PokemonProgressBarUi.class);
        });
    }

    static void resetProgressBarUi() {
        ApplicationManager.getApplication().invokeLater(() -> {
            UIManager.put(PROGRESS_BAR_UI_KEY, previousProgressBar);
            PokeballLoaderIconReplacer.updateSpinner(false);
        });
    }

    public static IdeaPluginDescriptor getPluginDescriptor() {
        return Optional.ofNullable(pluginId)
            .map(PluginManagerCore::getPlugin)
            .orElseGet(() -> PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID_STRING)));
    }
}
