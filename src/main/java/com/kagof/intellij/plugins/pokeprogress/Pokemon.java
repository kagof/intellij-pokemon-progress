package com.kagof.intellij.plugins.pokeprogress;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.util.text.StringUtil;

public enum Pokemon {
    // Gen I
    BULBASAUR(1, "bulbasaur", -16, -11, PokemonType.GRASS, PokemonType.POISON),
    VENUSAUR(3, "venusaur", -14, -7, PokemonType.GRASS, PokemonType.POISON),
    CHARMANDER(4, "charmander", -20, -11, PokemonType.FIRE),
    CHARIZARD(6, "charizard", -16, -8, PokemonType.FIRE, PokemonType.FLYING),
    SQUIRTLE(7, "squirtle", -16, -12, PokemonType.WATER),
    BLASTOISE(9, "blastoise", -19, -9, PokemonType.WATER),
    BUTTERFREE(12, "butterfree", -14, -9, PokemonType.BUG, PokemonType.FLYING),
    PIKACHU(25, "pikachu", -16, -11, PokemonType.ELECTRIC),
    NIDOQUEEN(31, "nidoqueen", -16, -10, PokemonType.POISON, PokemonType.GROUND),
    NIDOKING(34, "nidoking", -16, -10, PokemonType.POISON, PokemonType.GROUND),
    JIGGLYPUFF(39, "jigglypuff", -16, -11, PokemonType.NORMAL, PokemonType.FAIRY),
    MEOWTH(52, "meowth", -18, -11, PokemonType.NORMAL),
    ALAKAZAM(65, "alakazam", -16, -9, PokemonType.PSYCHIC),
    MACHAMP(68, "machamp", -16, -7, PokemonType.FIGHTING),
    SLOWPOKE(79, "slowpoke", -17, -11, PokemonType.WATER, PokemonType.PSYCHIC),
    MAGNEMITE(81, "magnemite", -16, -11, PokemonType.ELECTRIC, PokemonType.STEEL),
    GENGAR(94, "gengar", -16, -9, PokemonType.GHOST, PokemonType.POISON),
    KOFFING(109, "koffing", -16, -9, PokemonType.POISON),
    SCYTHER(123, "scyther", -17, -9, PokemonType.BUG, PokemonType.FLYING),
    GYARADOS(130, "gyarados", -16, -7, PokemonType.WATER, PokemonType.FLYING),
    EEVEE(133, "eevee", -16, -11, PokemonType.NORMAL),
    VAPOREON(134, "vaporeon", -18, -11, PokemonType.WATER),
    JOLTEON(135, "jolteon", -16, -11, PokemonType.ELECTRIC),
    FLAREON(136, "flareon", -18, -11, PokemonType.FIRE),
    SNORLAX(143, "snorlax", -16, -7, PokemonType.NORMAL),
    ARTICUNO(144, "articuno", -18, -7, PokemonType.ICE, PokemonType.FLYING),
    ZAPDOS(145, "zapdos", -16, -7, PokemonType.ELECTRIC, PokemonType.FLYING),
    MOLTRES(146, "moltres", -15, -6, PokemonType.FIRE, PokemonType.FLYING),
    DRAGONITE(149, "dragonite", -16, -7, PokemonType.DRAGON, PokemonType.FLYING),
    MEWTWO(150, "mewtwo", -16, -7, PokemonType.PSYCHIC),
    MEW(151, "mew", -16, -10, PokemonType.PSYCHIC),
    // Gen II
    CHIKORITA(152, "chikorita", -16, -10, PokemonType.GRASS),
    MEGANIUM(154, "meganium", -16, -8, PokemonType.GRASS),
    CYNDAQUIL(155, "cyndaquil", -16, -12, PokemonType.FIRE),
    TYPHOLSION(157, "typhlosion", -13, -8, PokemonType.FIRE),
    TOTODILE(158, "totodile", -16, -11, PokemonType.WATER),
    FERALIGATR(160, "feraligatr", -16, -7, PokemonType.WATER),
    TOGEPI(175, "togepi", -16, -11, PokemonType.FAIRY),
    ESPEON(196, "espeon", -16, -10, PokemonType.PSYCHIC),
    UMBREON(197, "umbreon", -16, -10, PokemonType.DARK),
    WOBBUFFET(202, "wobbuffet", -16, -10, PokemonType.PSYCHIC),
    RAIKOU(243, "raikou", -17, -10, PokemonType.ELECTRIC),
    ENTEI(244, "entei", -17, -10, PokemonType.FIRE),
    SUICUNE(245, "suicune", -19, -10, PokemonType.WATER),
    LUGIA(249, "lugia", -19, -10, PokemonType.PSYCHIC, PokemonType.FLYING),
    HO_OH(250, "ho-Oh", -18, -10, PokemonType.FIRE, PokemonType.FLYING),
    CELEBI(251, "celebi", -16, -10, PokemonType.PSYCHIC, PokemonType.GRASS),
    // Gen III
    WAILMER(320, "wailmer", -16, -9, PokemonType.WATER),
    WAILORD(321, "wailord", -35, -35, PokemonType.WATER),
    KYOGRE(382, "kyogre", -16, -5, PokemonType.WATER),
    GROUDON(383, "groudon", -18, -12, PokemonType.GROUND),
    RAYQUAZA(384, "rayquaza", -21, -12, PokemonType.DRAGON, PokemonType.FLYING),
    JIRACHI(385, "jirachi", -14, -10, PokemonType.STEEL, PokemonType.PSYCHIC),
    DEOXYS(386, "deoxys", -16, -7, PokemonType.PSYCHIC),
    // Gen IV
    LEAFEON(470, "leafeon", -18, -11, PokemonType.GRASS),
    GLACEON(471, "glaceon", -18, -11, PokemonType.ICE),
    // Gen V
    // Gen VI
    SYLVEON(700, "sylveon", -18, -8, PokemonType.FAIRY),
    // Gen VII
    MIMIKYU(778, "mimikyu", -21, -7, PokemonType.GHOST, PokemonType.FAIRY),
    // Gen VIII
    GROOKEY(810, "grookey", -15, -7, PokemonType.GRASS),
    RILLABOOM(812, "rillaboom", -15, -6, PokemonType.GRASS),
    SCORBUNNY(813, "scorbunny", -16, -7, PokemonType.FIRE),
    CINDERACE(815, "cinderace", -16, -7, PokemonType.FIRE),
    SOBBLE(816, "sobble", -16, -6, PokemonType.WATER),
    INTELLEON(818, "intelleon", -16, -6, PokemonType.WATER),
    WOOLOO(831, "wooloo", -12, -5, PokemonType.NORMAL),
    ZACIAN(888, "zacian", -10, -6, PokemonType.FAIRY, PokemonType.STEEL),
    ZAMAZENTA(889, "zamazenta", -7, -7, PokemonType.FIGHTING, PokemonType.STEEL),

    // Secret
    MISSINGNO(-1, "missingNo.", -16, -7, true, PokemonType.NORMAL);

    // Historically used for testing, however will soon be removed, as TestProgressBar.java is much more useful
    static final boolean DEBUGGING = false;
    static final Pokemon TARGET = null;

    public static final Map<String, Pokemon> DEFAULT_POKEMON = Arrays.stream(values())
        .filter(p -> !p.secret)
        .collect(ImmutableMap.toImmutableMap(Pokemon::getNumberString, Function.identity()));

    private final List<PokemonType> types;

    private final String name;
    private final int number;

    private final int xShift;
    private final int yShift;
    private final boolean secret;
    private final Generation generation;

    public static Pokemon getByNumber(final String number) {
        return DEFAULT_POKEMON.get(number);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final PokemonType... types) {
        this(number, name, xShift, yShift, false, types);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final boolean secret, final PokemonType... types) {
        if (types == null || types.length < 1) {
            throw new IllegalArgumentException("configuration for " + name + " invalid");
        }

        this.types = ImmutableList.copyOf(types);
        this.xShift = xShift;
        this.yShift = yShift;
        this.name = name;
        this.number = number;

        this.secret = secret;
        generation = Generation.getGeneration(number);
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

    public String getNameWithNumber() {
        return StringUtil.capitalizeWords(name, true) + " (#" + getNumberString() + ")";
    }

    @Override
    public String toString() {
        return getNameWithNumber();
    }

}
