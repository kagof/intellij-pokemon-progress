package com.kagof.intellij.plugins.pokeprogress.theme;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.kagof.intellij.plugins.pokeprogress.PokemonResourceLoader;
import com.kagof.intellij.plugins.pokeprogress.model.PokemonType;

/**
 * Each row of the CSV should be itype,light,mid,dark. Filename should be number_name.csv
 *
 * Note that although the files are CSV files, escaping & quotes are not respected
 */
public class ColorSchemes {
    private static final String COLOR_RESOURCE_PATH = "/com/kagof/intellij/plugins/pokeprogress/colors/";
    private static final String COLOR_INDEX = COLOR_RESOURCE_PATH + ".cscheme.index";
    private static final String OFFICIAL_THEME_ID = "1_Official";
    private static Map<String, ColorScheme> allSchemes;

    public static ColorScheme[] getAll() {
        return allSchemes.values().toArray(new ColorScheme[0]);
    }

    public static ColorScheme getByIdOrDefault(final String id) {
        return allSchemes.getOrDefault(id, getDefaultScheme());
    }

    public static String getDefaultSchemeId() {
        return OFFICIAL_THEME_ID;
    }

    public static ColorScheme getDefaultScheme() {
        return allSchemes.get(getDefaultSchemeId());
    }

    static {
        allSchemes = new LinkedHashMap<>(); // preserves order
        try (InputStream iSIdx = PokemonResourceLoader.getResourceAsStream(COLOR_INDEX).orElse(null)) {
            if (iSIdx == null) {
                throw new IllegalStateException("Color scheme index file not found");
            }
            getLinesStream(iSIdx)
                .sorted()
                .filter(s -> s.endsWith(".csv"))
                .forEach(s -> {
                    final LinkedHashMap<PokemonType, TypeColor> value = new LinkedHashMap<>();
                    try (InputStream is = PokemonResourceLoader.getResourceAsStream(COLOR_RESOURCE_PATH + s)
                        .orElse(null)) {
                        if (is == null) {
                            return;
                        }
                        getLinesStream(is).forEach(line -> {
                            final String[] split = line.split(",");
                            if (split.length != 4) {
                                throw new IllegalStateException("Color scheme " + s + " malformed");
                            }
                            value.put(PokemonType.valueOf(cleanCsvValue(split[0])),
                                new TypeColor(cleanCsvValue(split[1]),
                                    cleanCsvValue(split[2]),
                                    cleanCsvValue(split[3])));
                        });
                    } catch (final Exception e) {
                        throw new RuntimeException("Exception reading color scheme " + s, e);
                    }
                    final String id = s.replaceAll(".csv", "");
                    final String name = id.split("_", 2)[1].replace('_', ' ');
                    allSchemes.put(id, new ColorScheme(id, name, value));
                });
        } catch (final Exception e) {
            throw new RuntimeException("Exception reading color schemes", e);
        }
    }

    private static Stream<String> getLinesStream(final InputStream is) throws IOException {
        return Arrays.stream(new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n"));
    }

    private ColorSchemes() {}

    private static String cleanCsvValue(final String in) {
        return Optional.ofNullable(in)
            .map(s -> s.trim().toUpperCase(Locale.ROOT))
            .orElse(null);
    }
}
