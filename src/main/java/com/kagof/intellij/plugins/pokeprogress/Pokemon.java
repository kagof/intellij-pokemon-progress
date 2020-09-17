package com.kagof.intellij.plugins.pokeprogress;

import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.text.StringUtil;

public enum Pokemon {
    // Gen I
    BULBASAUR(1, "bulbasaur", -16, -12, PokemonType.GRASS, PokemonType.POISON),
    VENUSAUR(3, "venusaur", -14, -7, PokemonType.GRASS, PokemonType.POISON),
    CHARMANDER(4, "charmander", -20, -11, PokemonType.FIRE),
    CHARIZARD(6, "charizard", -16, -8, PokemonType.FIRE, PokemonType.FLYING),
    SQUIRTLE(7, "squirtle", -16, -12, PokemonType.WATER),
    BLASTOISE(9, "blastoise", -19, -9, PokemonType.WATER),
    PIKACHU(25, "pikachu", -16, -11, PokemonType.ELECTRIC),
    EEVEE(133, "eevee", -16, -11, PokemonType.NORMAL),
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

    private static final String RESOURCE_PATH = "/com/kagof/intellij/plugins/pokeprogress/sprites/";
    private static final Random RANDOM = new Random();

    private final Icon icon;
    private final Icon iconR;

    private final List<PokemonType> types;

    private final String name;

    private final int number;

    private final int xShift;
    private final int yShift;

    public static Pokemon randomPokemon() {
        return Pokemon.values()[RANDOM.nextInt(Pokemon.values().length)];
    }

    Pokemon(final int number, final String name, final int xShift, final int yShift, final PokemonType... types) {
        if (types == null || types.length < 1) {
            throw new IllegalArgumentException("configuration for " + name + " invalid");
        }
        this.icon = IconLoader.getIcon(RESOURCE_PATH + name + ".gif");
        this.iconR = IconLoader.getIcon(RESOURCE_PATH + name + "_r.gif");
        this.types = ImmutableList.copyOf(types);
        this.xShift = xShift;
        this.yShift = yShift;
        this.name = StringUtil.capitalizeWords(name, true);
        this.number = number;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public Icon getIcon() {
        return icon;
    }

    public Icon getIconR() {
        return iconR;
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
        return name + " (#" + number + ")";
    }
}
