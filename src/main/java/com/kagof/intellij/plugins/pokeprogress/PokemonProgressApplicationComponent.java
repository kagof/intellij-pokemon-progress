package com.kagof.intellij.plugins.pokeprogress;

import javax.swing.UIManager;

import com.intellij.ide.ui.LafManagerListener;
import com.intellij.openapi.application.ApplicationManager;

public class PokemonProgressApplicationComponent {
    public PokemonProgressApplicationComponent() {
        ApplicationManager.getApplication().getMessageBus().connect().subscribe(LafManagerListener.TOPIC,
            __ -> updateProgressBarUi());
        updateProgressBarUi();
    }

    private void updateProgressBarUi() {
        UIManager.put("ProgressBarUI", PokemonProgressBarUi.class.getName());
        UIManager.getDefaults().put(PokemonProgressBarUi.class.getName(), PokemonProgressBarUi.class);
    }
}
