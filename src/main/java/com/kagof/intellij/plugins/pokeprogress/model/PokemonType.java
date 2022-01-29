package com.kagof.intellij.plugins.pokeprogress.model;

import java.awt.Color;

public enum PokemonType {
    NORMAL("#C6C6A7", "#A8A878", "#6D6D4E"),
    FIGHTING("#D67873", "#C03028", "#7D1F1A"),
    FLYING("#C6B7F5", "#A890F0", "#6D5E9C"),
    POISON("#C183C1", "#A040A0", "#682A68"),
    GROUND("#EBD69D", "#927D44", "#927D44"),
    ROCK("#D1C17D", "#B8A038", "#786824"),
    BUG("#C6D16E", "#A8B820", "#6D7815"),
    GHOST("#A292BC", "#705898", "#493963"),
    STEEL("#D1D1E0", "#B8B8D0", "#787887"),
    FIRE("#F5AC78", "#F08030", "#9C531F"),
    WATER("#9DB7F5", "#6890F0", "#445E9C"),
    GRASS("#A7DB8D", "#78C850", "#4E8234"),
    ELECTRIC("#FAE078", "#F8D030", "#A1871F"),
    PSYCHIC("#FA92B2", "#F85888", "#A13959"),
    ICE("#BCE6E6", "#98D8D8", "#638D8D"),
    DRAGON("#A27DFA", "#7038F8", "#4924A1"),
    DARK("#A29288", "#705848", "#49392F"),
    FAIRY("#F4BDC9", "#EE99AC", "#9B6470");

    private final Color colorLight;
    private final Color color;
    private final Color colorDark;

    PokemonType(final String hexColorLight, final String hexColor, final String hexColorDark) {
        colorLight = Color.decode(hexColorLight);
        color = Color.decode(hexColor);
        colorDark = Color.decode(hexColorDark);
    }

    public Color getColorLight() {
        return colorLight;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorDark() {
        return colorDark;
    }
}
