package com.kagof.intellij.plugins.pokeprogress;

import com.intellij.openapi.util.text.Strings;
import com.kagof.intellij.plugins.pokeprogress.model.Generation;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorScheme;
import com.kagof.intellij.plugins.pokeprogress.theme.ColorSchemes;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintTheme;
import com.kagof.intellij.plugins.pokeprogress.theme.PaintThemes;
import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.nio.AnimatedGif;
import com.sksamuel.scrimage.nio.AnimatedGifReader;
import com.sksamuel.scrimage.nio.ImageSource;
import com.sksamuel.scrimage.nio.StreamingGifWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.junit.Assume;
import org.junit.Test;

/**
 * Updates the readme, family picture, and release notes to include new versions and new Pokémon.
 * <p>
 * Use the "Generate Documentation" IntelliJ run configuration.
 */
// deliberately not following naming convention so doesn't run automatically
@SuppressWarnings("NewClassNamingConvention")
public class DocumentationGenerator {
    private static final ColorScheme FAMILY_COLOR_SCHEME = ColorSchemes.getByIdOrDefault("1_Official");
    private static final PaintTheme FAMILY_PAINT_THEME = PaintThemes.getByIdOrDefault("flat");

    @Test
    public void runDocumentationGenerator() throws Exception {
        Assume.assumeTrue("KGIJ_DG_ENABLED environment variable not set",
            System.getenv().containsKey("KGIJ_DG_ENABLED"));
        updateFamilyPicture();
        updateReadme();
        addNewReleaseNoteSection();
    }

    /**
     * Updates the sprites in the README.md file.
     */
    public static void updateReadme() throws IOException {
        final Path readme = new File("README.md").toPath();
        final String content = Files.readString(readme, Charset.defaultCharset());
        final int start = content.lastIndexOf("## Included Pokémon");
        final int end = content.lastIndexOf("[comment]: <> (end-included-pokemon)") + 36;
        final String substring = content.substring(start, end);
        final String newContent = content.replace(substring, getReadmeString());
        Files.writeString(readme, newContent, Charset.defaultCharset());
        System.out.println("replaced content of README.md");
    }

    /**
     * Updates the sprites in the family picture.
     */
    public static void updateFamilyPicture() throws Exception {
        final int lcm = getLcmNumFrames();
        final List<BufferedImage> images = new ArrayList<>();
        for (int frame = 0; frame < lcm; frame++) {
            images.add(drawFrame(frame));
        }

        final StreamingGifWriter writer = new StreamingGifWriter(Duration.ofMillis(500), true, false);
        try (final StreamingGifWriter.GifStream gif = writer.prepareStream("eg/family.gif",
            BufferedImage.TYPE_INT_ARGB)) {
            for (final BufferedImage image : images) {
                gif.writeFrame(ImmutableImage.fromAwt(image));
            }
        }
        System.out.println("updated family photo");
    }

    public static void addNewReleaseNoteSection() throws IOException {
        final Path gradleFile = new File("gradle.properties").toPath();
        final String gradleContents = Files.readString(gradleFile, Charset.defaultCharset());
        final String version = gradleContents.lines()
            .filter(l -> l.startsWith("pluginVersion"))
            .map(l -> {
                final Matcher m = Pattern.compile("=.*").matcher(l);
                if (m.find()) {
                    return m.group();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .filter(Strings::isNotEmpty)
            .findFirst()
            .map(s -> s.replaceAll("\"", "").replaceAll("=", ""))
            .orElseThrow(() -> new IllegalStateException("pluginVersion not found in gradle.properties"));
        System.out.println("using version " + version);

        final Path changenotes = new File("changenotes.html").toPath();
        final String content = Files.readString(changenotes, Charset.defaultCharset());
        if (content.lines().anyMatch(l -> l.contains("<!--" + version + "-->"))) {
            System.out.println("changenotes already includes this version");
            return;
        }

        final String newContent = getNewContent(version, content);
        Files.writeString(changenotes, newContent, Charset.defaultCharset());
        System.out.println("added " + version + " section to changenotes.html");
    }

    private static @NotNull String getNewContent(final String version, final String content) {
        final String newNotes = "    <li>\n"
            + "        <b>\n"
            + "            <a href=\"https://github.com/kagof/intellij-pokemon-progress/releases/tag/"
            + version
            + "\">\n"
            + "                "
            + version
            + "\n"
            + "            </a>\n"
            + "        </b>\n"
            + "        <!--"
            + version
            + "-->\n"
            + "        <ul>\n"
            + "            <li></li>\n"
            + "        </ul>\n"
            + "        <!--/"
            + version
            + "-->\n"
            + "    </li>\n";

        return content
            .replace("<details open>", "<details>")
            .replaceFirst("<ul>\n", "<ul>\n" + newNotes)
            .replaceFirst("<ul>\r\n", "<ul>\r\n" + newNotes);
    }

    private static String getReadmeString() {
        final Map<Generation, Boolean> gens = Arrays.stream(Generation.values())
            .collect(Collectors.toMap(Function.identity(), __ -> false));
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("## Included Pokémon\n");
        for (final Pokemon pokemon : Pokemon.values()) {
            final Generation generation = pokemon.getGeneration();
            if (!gens.get(generation)) {
                stringBuilder.append("\n### Generation ").append(generation).append("\n\n");
                gens.put(generation, true);
            }
            if (!pokemon.isSecret()) {
                stringBuilder.append(getReadmeString(pokemon)).append("\n");
            }
        }
        stringBuilder.append("\n");
        stringBuilder.append("[comment]: <> (end-included-pokemon)");
        return stringBuilder.toString().trim();
    }

    private static String getReadmeString(final Pokemon pokemon) {
        return String.format(
            "* ![%s](src/main/resources/%s) %s ![%s](src/main/resources/%s)",
            pokemon.getNameWithNumber(),
            PokemonResourceLoader.getIconPath(pokemon),
            pokemon.getNameWithNumber(),
            pokemon.getNameWithNumber(),
            PokemonResourceLoader.getReversedIconPath(pokemon));
    }

    private static BufferedImage drawFrame(final int frame) throws IOException {
        int i = 0;
        int j = 0;
        final int numPokemon = Pokemon.DEFAULT_POKEMON.size();
        final BufferedImage img = new BufferedImage(10 * 32,
            (numPokemon % 10 == 0) ? (numPokemon / 10 * 32) : ((numPokemon / 10 * 32) + 32),
            BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = (Graphics2D) img.getGraphics();
        for (final Pokemon pokemon : Pokemon.DEFAULT_POKEMON.values()) {
            if (i != 0 && i % 10 == 0) {
                j++;
                i = 0;
            }
            drawPokemon(frame, i, j, g, pokemon);
            i++;
        }
        return img;
    }

    private static void drawPokemon(final int frame, final int i, final int j, final Graphics2D g, final Pokemon pokemon)
        throws IOException {
        final int startX = i * 32;
        final int startY = j * 32;
        final Paint typePaint = FAMILY_PAINT_THEME.getPaint(pokemon.getTypes(), FAMILY_COLOR_SCHEME, startY, 32);
        g.setPaint(typePaint);
        g.fillRect(startX, startY, 32, 32);
        final AnimatedGif gif = AnimatedGifReader.read(ImageSource.of(DocumentationGenerator.class.getClassLoader()
            .getResource(PokemonResourceLoader.getIconPath(pokemon))
            .openStream()));
        g.drawImage(gif.getFrame(frame % gif.getFrameCount()).awt(), startX, startY, 32, 32, null);
    }

    private static int getLcmNumFrames() {
        // If we ever get sprites with more frames, we should actually implement this method properly
        return 2;
    }
}
