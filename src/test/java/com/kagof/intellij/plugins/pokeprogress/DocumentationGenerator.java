package com.kagof.intellij.plugins.pokeprogress;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("This is not actually a test - just a handy tool for generating the README entries for new Pokemon")
public class DocumentationGenerator {

    @Test
    public void printForReadme() {
        final Map<Generation, Boolean> gens = Arrays.stream(Generation.values()).collect(Collectors.toMap(Function.identity(), __ -> false));
        for (final Pokemon pokemon : Pokemon.values()) {
            final Generation generation = pokemon.getGeneration();
            if (!gens.get(generation)) {
                System.out.println();
                System.out.println("### Generation " + generation);
                System.out.println();
                gens.put(generation, true);
            }
            if (!pokemon.isSecret()) {
                System.out.println(getReadmeString(pokemon));
            }
        }
        System.out.println();
    }

    @Test
    public void printForDescriptionHtml() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div>\n    ");
        int i = 0;
        for (final Pokemon pokemon : Pokemon.values()) {
            if (!pokemon.isSecret()) {
                if (i != 0 && i % 10 == 0) {
                    stringBuilder.append("<br>\n    ");
                }
                ++i;
                stringBuilder.append(getPluginXmlString(pokemon));
            }
        }
        stringBuilder.append("\n</div>");
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
