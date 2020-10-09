package com.kagof.intellij.plugins.pokeprogress;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
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
    WOBBUFFET(202, "wobbuffet", -16, -10, PokemonType.PSYCHIC),
    // Gen III
    WAILMER(320, "wailmer", -16, -9, PokemonType.WATER),
    WAILORD(321, "wailord", -35, -35, PokemonType.WATER),
    KYOGRE(384, "kyogre", -35, -35, PokemonType.WATER),
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
    MISSINGNO("???", "missingNo.", -16, -7, true, PokemonType.NORMAL);

    // For convenience's sake, these can be used when testing positioning & sizing of new sprites
    static final boolean DEBUGGING = false;
    static final Pokemon TARGET = null;

    public static final Map<String, Pokemon> DEFAULT_POKEMON = Arrays.stream(values()).filter(p -> !p.secret)
        .collect(Collectors.toMap(Pokemon::getNumber, Function.identity()));

    private final List<PokemonType> types;

    private final String name;
    private final String number;

    private final int xShift;
    private final int yShift;
    private final boolean secret;

    public static Pokemon getByNumber(final String number) {
        return DEFAULT_POKEMON.get(number);
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final PokemonType... types) {
        this(String.format("%03d", number), name, xShift, yShift, false, types);
    }

    Pokemon(final String number, final String name, final int xShift, final int yShift, final boolean secret, final PokemonType... types) {
        if (types == null || types.length < 1) {
            throw new IllegalArgumentException("configuration for " + name + " invalid");
        }

        this.types = ImmutableList.copyOf(types);
        this.xShift = xShift;
        this.yShift = yShift;
        this.name = name;
        this.number = number;

        this.secret = secret;
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

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public boolean isSecret() {
        return secret;
    }

    public String getNameWithNumber() {
        return StringUtil.capitalizeWords(name, true) + " (#" + number + ")";
    }
}
