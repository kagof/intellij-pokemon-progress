package com.kagof.intellij.plugins.pokeprogress;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.swing.Icon;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.util.IconLoader;
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
    MEOWTH(52, "meowth", -18, -11, PokemonType.NORMAL),
    SLOWPOKE(79, "slowpoke", -17, -11, PokemonType.WATER, PokemonType.PSYCHIC),
    GENGAR(94, "gengar", -16, -9, PokemonType.GHOST, PokemonType.POISON),
    KOFFING(109, "koffing", -16, -9, PokemonType.POISON),
    GYARADOS(130, "gyarados", -16, -7, PokemonType.WATER, PokemonType.FLYING),
    EEVEE(133, "eevee", -16, -11, PokemonType.NORMAL),
    SNORLAX(143, "snorlax", -16, -7, PokemonType.NORMAL),
    ARTICUNO(144, "articuno", -18, -7, PokemonType.ICE, PokemonType.FLYING),
    ZAPDOS(145, "zapdos", -16, -7, PokemonType.ELECTRIC, PokemonType.FLYING),
    MOLTRES(146, "moltres", -15, -6, PokemonType.FIRE, PokemonType.FLYING),
    MEWTWO(150, "mewtwo", -16, -7, PokemonType.PSYCHIC),
    MEW(151, "mew", -16, -10, PokemonType.PSYCHIC),
    // Gen II
    CHIKORITA(152, "chikorita", -16, -10, PokemonType.GRASS),
    MEGANIUM(154, "meganium", -16, -8, PokemonType.GRASS),
    CYNDAQUIL(155, "cyndaquil", -16, -12, PokemonType.FIRE),
    TYPHOLSION(157, "typhlosion", -13, -8, PokemonType.FIRE),
    TOTODILE(158, "totodile", -16, -11, PokemonType.WATER),
    FERALIGATR(160, "feraligatr", -16, -7, PokemonType.GRASS, PokemonType.FIRE, PokemonType.WATER),
    TOGEPI(175, "togepi", -16, -11, PokemonType.FAIRY),
    WOBBUFFET(202, "wobbuffet", -16, -10, PokemonType.PSYCHIC),
    // Gen III
    WAILMER(320, "wailmer", -16, -9, PokemonType.WATER),
    WAILORD(321, "wailord", -35, -35, PokemonType.WATER),
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
    ZAMAZENTA(889, "zamazenta", -7, -7, PokemonType.FIGHTING, PokemonType.STEEL);

    // For convenience's sake, these can be used when testing positioning & sizing of new sprites
    static final boolean DEBUGGING = false;
    private static final Pokemon TARGET = null;

    private static final String RESOURCE_PATH = "/com/kagof/intellij/plugins/pokeprogress/sprites/";
    private static final Random RANDOM = new Random();

    private final Supplier<Icon> icon;
    private final Supplier<Icon> iconR;

    private final List<PokemonType> types;

    private final String name;

    private final int number;

    private final int xShift;
    private final int yShift;

    @SuppressWarnings("ConstantConditions")
    public static Pokemon randomPokemon() {
        return TARGET == null ? Pokemon.values()[RANDOM.nextInt(Pokemon.values().length)] : TARGET;
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final PokemonType... types) {
        if (types == null || types.length < 1) {
            throw new IllegalArgumentException("configuration for " + name + " invalid");
        }

        this.types = ImmutableList.copyOf(types);
        this.xShift = xShift;
        this.yShift = yShift;
        this.name = name;
        this.number = number;

        this.icon = () -> IconLoader.getIcon(RESOURCE_PATH + name + ".gif");
        this.iconR = () -> IconLoader.getIcon(RESOURCE_PATH + name + "_r.gif");
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public Icon getIcon() {
        return icon.get();
    }

    public Icon getIconR() {
        return iconR.get();
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

    public int getNumber() {
        return number;
    }

    public String getNameWithNumber() {
        return StringUtil.capitalizeWords(name, true) + " (#" + number + ")";
    }
}
