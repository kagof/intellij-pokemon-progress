package com.kagof.intellij.plugins.pokeprogress;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;

public class PokemonPicker {
    private static final Random RANDOM = new Random();

    @SuppressWarnings("ConstantConditions")
    public static Pokemon get() {
        if (Pokemon.TARGET != null) {
            return Pokemon.TARGET;
        }

        final List<String> enabledPokemonNumbers = Optional.ofNullable(PokemonProgressState.getInstance())
            .map(PokemonPicker::getEnabledPokemonNumbers)
            .orElse(null);
        if (enabledPokemonNumbers == null || enabledPokemonNumbers.isEmpty()) {
            return Pokemon.MISSINGNO;
        }
        return Pokemon.getByNumber(enabledPokemonNumbers.get(RANDOM.nextInt(enabledPokemonNumbers.size())));
    }

    private static List<String> getEnabledPokemonNumbers(final PokemonProgressState state) {
        return state.getPokemonNumbersEnabled().entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
