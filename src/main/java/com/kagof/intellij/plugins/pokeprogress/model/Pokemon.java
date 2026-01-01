package com.kagof.intellij.plugins.pokeprogress.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public enum Pokemon {
    // Gen I
    BULBASAUR(1, "bulbasaur", -18, -13, 18, PokemonType.GRASS, PokemonType.POISON),
    VENUSAUR(3, "venusaur", -16, -5, 25, PokemonType.GRASS, PokemonType.POISON),
    CHARMANDER(4, "charmander", -19, -12, 20, PokemonType.FIRE),
    CHARIZARD(6, "charizard", -16, -5, 25, PokemonType.FIRE, PokemonType.FLYING),
    SQUIRTLE(7, "squirtle", -16, -12, 20, PokemonType.WATER),
    BLASTOISE(9, "blastoise", -15, -6, 25, PokemonType.WATER),
    BUTTERFREE(12, "butterfree", -12, -8, 21, PokemonType.BUG, PokemonType.FLYING),
    PIKACHU(25, "pikachu", -15, -11, 20, PokemonType.ELECTRIC),
    NIDOQUEEN(31, "nidoqueen", -16, -7, 24, PokemonType.POISON, PokemonType.GROUND),
    NIDOKING(34, "nidoking", -14, -7, 24, PokemonType.POISON, PokemonType.GROUND),
    JIGGLYPUFF(39, "jigglypuff", -16, -13, 18, PokemonType.NORMAL, PokemonType.FAIRY),
    DIGLETT(50, "diglett", -15, -16, 16, PokemonType.GROUND),
    DUGTRIO(51, "dugtrio", -13, -9, 21, PokemonType.GROUND),
    MEOWTH(52, "meowth", -19, -11, 20, PokemonType.NORMAL),
    PSYDUCK(54, "psyduck", -17, -10, 22, PokemonType.WATER),
    GOLDUCK(55, "golduck", -15, -8, 24, PokemonType.WATER),
    ALAKAZAM(65, "alakazam", -16, -6, 25, PokemonType.PSYCHIC),
    MACHAMP(68, "machamp", -16, -5, 25, PokemonType.FIGHTING),
    SLOWPOKE(79, "slowpoke", -17, -14, 18, PokemonType.WATER, PokemonType.PSYCHIC),
    MAGNEMITE(81, "magnemite", -17, -13, 18, PokemonType.ELECTRIC, PokemonType.STEEL),
    GENGAR(94, "gengar", -16, -7, 24, PokemonType.GHOST, PokemonType.POISON),
    KOFFING(109, "koffing", -16, -8, 21, PokemonType.POISON),
    SCYTHER(123, "scyther", -15, -6, 25, PokemonType.BUG, PokemonType.FLYING),
    GYARADOS(130, "gyarados", -16, -1, 30, PokemonType.WATER, PokemonType.FLYING),
    EEVEE(133, "eevee", -15, -12, 20, PokemonType.NORMAL),
    VAPOREON(134, "vaporeon", -19, -8, 24, PokemonType.WATER),
    JOLTEON(135, "jolteon", -14, -10, 21, PokemonType.ELECTRIC),
    FLAREON(136, "flareon", -20, -8, 24, PokemonType.FIRE),
    SNORLAX(143, "snorlax", -16, -1, 30, PokemonType.NORMAL),
    ARTICUNO(144, "articuno", -18, -5, 24, PokemonType.ICE, PokemonType.FLYING),
    ZAPDOS(145, "zapdos", -11, -7, 24, PokemonType.ELECTRIC, PokemonType.FLYING),
    MOLTRES(146, "moltres", -16, -2, 24, PokemonType.FIRE, PokemonType.FLYING),
    DRAGONITE(149, "dragonite", -18, -4, 26, PokemonType.DRAGON, PokemonType.FLYING),
    MEWTWO(150, "mewtwo", -16, -3, 28, PokemonType.PSYCHIC),
    MEW(151, "mew", -18, -8, 22, PokemonType.PSYCHIC),
    // Gen II
    CHIKORITA(152, "chikorita", -16, -10, 22, PokemonType.GRASS),
    MEGANIUM(154, "meganium", -16, -3, 28, PokemonType.GRASS),
    CYNDAQUIL(155, "cyndaquil", -14, -12, 20, PokemonType.FIRE),
    TYPHOLSION(157, "typhlosion", -13, -5, 26, PokemonType.FIRE),
    TOTODILE(158, "totodile", -16, -11, 20, PokemonType.WATER),
    FERALIGATR(160, "feraligatr", -17, -3, 28, PokemonType.WATER),
    TOGEPI(175, "togepi", -16, -12, 20, PokemonType.FAIRY),
    ESPEON(196, "espeon", -17, -9, 22, PokemonType.PSYCHIC),
    UMBREON(197, "umbreon", -18, -9, 22, PokemonType.DARK),
    WOBBUFFET(202, "wobbuffet", -18, -8, 24, PokemonType.PSYCHIC),
    SCIZOR(212, "scizor", -13, -5, 26, PokemonType.BUG, PokemonType.STEEL),
    RAIKOU(243, "raikou", -18, -7, 24, PokemonType.ELECTRIC),
    ENTEI(244, "entei", -18, -7, 24, PokemonType.FIRE),
    SUICUNE(245, "suicune", -20, -8, 24, PokemonType.WATER),
    LUGIA(249, "lugia", -30, -4, 30, PokemonType.PSYCHIC, PokemonType.FLYING),
    HO_OH(250, "ho-Oh", -22, -8, 30, PokemonType.FIRE, PokemonType.FLYING),
    CELEBI(251, "celebi", -15, -9, 22, PokemonType.PSYCHIC, PokemonType.GRASS),
    // Gen III
    TREECKO(252, "treecko", -19, -12, 20, PokemonType.GRASS),
    SCEPTILE(254, "sceptile", -14, -4, 28, PokemonType.GRASS),
    TORCHIC(255, "torchic", -17, -10, 22, PokemonType.FIRE),
    BLAZIKEN(257, "blaziken", -18, -5, 26, PokemonType.FIRE, PokemonType.FIGHTING),
    MUDKIP(258, "mudkip", -15, -12, 20, PokemonType.WATER),
    SWAMPERT(260, "swampert", -20, -4, 28, PokemonType.WATER, PokemonType.GROUND),
    WAILMER(320, "wailmer", -16, -8, 22, PokemonType.WATER),
    WAILORD(321, "wailord", -32, -17, 24, PokemonType.WATER),
    LATIAS(380, "latias", -7, -2, 28, PokemonType.DRAGON, PokemonType.PSYCHIC),
    LATIOS(381, "latios", -7, -1, 28, PokemonType.DRAGON, PokemonType.PSYCHIC),
    KYOGRE(382, "kyogre", -22, -11, 30, PokemonType.WATER),
    GROUDON(383, "groudon", -24, -15, 32, PokemonType.GROUND),
    RAYQUAZA(384, "rayquaza", -32, -12, 32, PokemonType.DRAGON, PokemonType.FLYING),
    JIRACHI(385, "jirachi", -14, -9, 22, PokemonType.STEEL, PokemonType.PSYCHIC),
    DEOXYS(386, "deoxys", -16, -4, 28, PokemonType.PSYCHIC),
    // Gen IV
    TURTWIG(387, "turtwig", -16, -9, 22, PokemonType.GRASS),
    TORTERRA(389, "torterra", -16, -5, 26, PokemonType.GRASS, PokemonType.GROUND),
    CHIMCHAR(390, "chimchar", -16, -9, 22, PokemonType.FIRE),
    INFERNAPE(392, "infernape", -19, -3, 28, PokemonType.FIRE, PokemonType.FIGHTING),
    PIPLUP(393, "piplup", -16, -11, 20, PokemonType.WATER),
    EMPOLEON(395, "empoleon", -18, -3, 28, PokemonType.WATER, PokemonType.STEEL),
    LEAFEON(470, "leafeon", -19, -10, 22, PokemonType.GRASS),
    GLACEON(471, "glaceon", -16, -9, 22, PokemonType.ICE),
    ARCEUS(493, "arceus", -24, 0, 32, PokemonType.NORMAL),
    // Gen V
    SNIVY(495, "snivy", -11, 0, 20, PokemonType.GRASS),
    SERPERIOR(497, "serperior", -20, -7, 26, PokemonType.GRASS),
    TEPIG(498, "tepig", -16, -1, 20, PokemonType.FIRE),
    EMBOAR(500, "emboar", -19, -4, 27, PokemonType.FIRE, PokemonType.FIGHTING),
    OSHAWOTT(501, "oshawott", -11, -2, 20, PokemonType.WATER),
    SAMUROTT(503, "samurott", -17, -5, 28, PokemonType.WATER),
    // Gen VI
    CHESPIN(650, "chespin", -18, -11, 20, PokemonType.GRASS),
    CHESNAUGHT(652, "chesnaught", -18, -6, 25, PokemonType.GRASS, PokemonType.FIGHTING),
    FENNEKIN(653, "fennekin", -18, -11, 20, PokemonType.FIRE),
    DELPHOX(655, "delphox", -16, -3, 28, PokemonType.FIRE, PokemonType.PSYCHIC),
    FROAKIE(656, "froakie", -20, -12, 18, PokemonType.WATER),
    GRENINJA(658, "greninja", -19, -3, 26, PokemonType.WATER, PokemonType.DARK),
    SYLVEON(700, "sylveon", -19, -6, 22, PokemonType.FAIRY),
    // Gen VII
    ROWLET(722, "rowlet", -15, -11, 20, PokemonType.GRASS, PokemonType.FLYING),
    DECIDUEYE(724, "decidueye", -15, -2, 28, PokemonType.GRASS, PokemonType.GHOST),
    LITTEN(725, "litten", -21, -9, 22, PokemonType.FIRE),
    INCINEROAR(727, "incineroar", -19, -4, 26, PokemonType.FIRE, PokemonType.DARK),
    POPPLIO(728, "popplio", -18, -8, 22, PokemonType.WATER),
    PRIMARINA(730, "primarina", -19, -5, 26, PokemonType.WATER, PokemonType.FAIRY),
    MIMIKYU(778, "mimikyu", -14, -10, 20, PokemonType.GHOST, PokemonType.FAIRY),
    // Gen VIII
    GALARIAN_ARTICUNO(144, "galarian articuno", -21, -2, 26, RegionalVariant.GALARIAN, PokemonType.PSYCHIC,
        PokemonType.FLYING),
    GALARIAN_ZAPDOS(145, "galarian zapdos", -16, -6, 26, RegionalVariant.GALARIAN, PokemonType.FIGHTING,
        PokemonType.FLYING),
    GALARIAN_MOLTRES(146, "galarian moltres", -17, -2, 26, RegionalVariant.GALARIAN, PokemonType.DARK,
        PokemonType.FLYING),
    GROOKEY(810, "grookey", -15, -6, 22, PokemonType.GRASS),
    RILLABOOM(812, "rillaboom", -15, -1, 28, PokemonType.GRASS),
    SCORBUNNY(813, "scorbunny", -16, -4, 26, PokemonType.FIRE),
    CINDERACE(815, "cinderace", -16, -1, 28, PokemonType.FIRE),
    SOBBLE(816, "sobble", -16, -5, 22, PokemonType.WATER),
    INTELLEON(818, "intelleon", -17, -3, 28, PokemonType.WATER),
    WOOLOO(831, "wooloo", -12, -5, 20, PokemonType.NORMAL),
    CRAMORANT(845, "cramorant", -17, -8, 25, PokemonType.FLYING, PokemonType.WATER),
    ZACIAN(888, "zacian", -10, -2, 26, PokemonType.FAIRY, PokemonType.STEEL),
    ZAMAZENTA(889, "zamazenta", -17, 0, 28, PokemonType.FIGHTING, PokemonType.STEEL),
    // Gen IX (numbers in this gen are speculation for now)
    SPRIGATITO(906, "sprigatito", -18, -11, 22, PokemonType.GRASS),
    MEOWSCARADA(908, "meowscarada", -15, -3, 28, PokemonType.GRASS, PokemonType.DARK),
    FUECOCO(909, "fuecoco", -15, -9, 22, PokemonType.FIRE),
    SKELEDIRGE(911, "skeledirge", -15, -10, 20, PokemonType.FIRE, PokemonType.GHOST),
    QUAXLY(912, "quaxly", -15, -11, 22, PokemonType.WATER),
    QUAQUAVAL(914, "quaquaval", -17, -1, 28, PokemonType.WATER, PokemonType.FIGHTING),
    // Secret
    MISSINGNO(-1, "missingNo.", -20, 0, 35, true, null, null, PokemonType.NORMAL);

    public static final Map<String, Pokemon> DEFAULT_POKEMON = Arrays.stream(values())
        .filter(p -> !p.secret)
        .collect(ImmutableMap.toImmutableMap(Pokemon::getId, Function.identity(), (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate Pokemon ID %s", u));
        }));

    private final List<PokemonType> types;

    private final String name;
    private final int number;
    private final String id;

    private final int xShift;
    private final int yShift;
    private final int height;
    private final boolean secret;
    private final Generation generation;

    public static Pokemon getById(final String id) {
        return DEFAULT_POKEMON.get(id);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final int height,
            final PokemonType... types) {
        this(number, name, xShift, yShift, height, null, types);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final int height,
            final RegionalVariant regionalVariant, final PokemonType... types) {
        this(number,
            name,
            xShift,
            yShift,
            height,
            false,
            Optional.ofNullable(regionalVariant).map(Enum::toString).orElse(null),
            Optional.ofNullable(regionalVariant).map(RegionalVariant::getGeneration).orElse(null),
            types);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final int height,
            final boolean secret, final String idModifier, final Generation gen, final PokemonType... types) {
        if (types == null || types.length < 1) {
            throw new IllegalArgumentException("configuration for " + name + " invalid");
        }

        this.types = ImmutableList.copyOf(types);
        this.xShift = xShift;
        this.yShift = yShift;
        this.height = height;
        this.name = name;
        this.number = number;
        id = getNumberString() + Optional.ofNullable(idModifier).map(m -> "_" + m).orElse("");

        this.secret = secret;
        generation = Optional.ofNullable(gen).orElseGet(() -> Generation.getGeneration(number));
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public int getXShift() {
        return xShift;
    }

    public int getYShift() {
        return yShift;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSecret() {
        return secret;
    }

    public Generation getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberString() {
        return number > 0 ? String.format("%03d", number) : "???";
    }

    public String getId() {
        return id;
    }

    public String getNameWithNumber() {
        return StringUtil.capitalizeWords(name, true) + " (#" + getNumberString() + ")";
    }

    @Override
    public String toString() {
        return getNameWithNumber();
    }

}
