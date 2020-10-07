package com.kagof.intellij.plugins.pokeprogress;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("This is not actually a test - just a handy tool for generating the README entries for new Pokemon")
public class DocumentationGenerator {

    @Test
    public void printForReadme() {
        for (final Pokemon pokemon : Pokemon.values()) {
            if (!pokemon.isSecret()) {
                System.out.println(getReadmeString(pokemon));
            }
        }
    }

    @Test
    public void printForPluginXml() {
        final StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (final Pokemon pokemon : Pokemon.values()) {
            if (!pokemon.isSecret()) {
                if (i == 0) {
                    stringBuilder.append("<br>\n    ");
                }
                i = (i + 1) % 10;
                stringBuilder.append(getPluginXmlString(pokemon));
            }
        }
        System.out.println(stringBuilder.toString().trim());
    }

    private String getReadmeString(final Pokemon pokemon) {
        return String.format(
            "* ![%s](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/%s.gif) %s ![%s](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/%s_r.gif)",
            pokemon.getNameWithNumber(),
            pokemon.getName(),
            pokemon.getNameWithNumber(),
            pokemon.getNameWithNumber(),
            pokemon.getName());
    }

    private String getPluginXmlString(final Pokemon pokemon) {
        return String.format(
            "<img src=\"https://raw.githubusercontent.com/kagof/intellij-pokemon-progress/master/src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/%s.gif\" title=\"%s\">",
            pokemon.getName(),
            pokemon.getNameWithNumber());
    }
}
