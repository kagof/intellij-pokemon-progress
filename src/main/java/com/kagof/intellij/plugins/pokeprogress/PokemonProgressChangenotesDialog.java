package com.kagof.intellij.plugins.pokeprogress;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JComponent;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;

public class PokemonProgressChangenotesDialog extends DialogWrapper {
    public static final String CHANGENOTES_URL = "https://raw.githubusercontent.com/kagof/intellij-pokemon-progress/master/changenotes.html";

    public PokemonProgressChangenotesDialog(@Nullable final Project project) {
        super(project);
        setTitle("Pok\u00E9mon Progress Changenotes");
        init();

    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        String text;
        try (InputStream inputStream = new URL(CHANGENOTES_URL).openStream()) {
            text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            text = "Changelog not found";
        }

        final JBLabel label = new JBLabel(text);
        label.setCopyable(true);
        return new JBScrollPane(label,
            JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
