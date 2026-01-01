package com.kagof.intellij.plugins.pokeprogress;

import com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState;
import com.kagof.intellij.plugins.pokeprogress.model.Pokemon;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class PokemonPicker {
    private static final String TARGET_ENV_VAR = "POKEMON_PROGRESS_TARGET";

    private static final Random RANDOM = new Random();

    public static Pokemon get() {
        final String target = System.getenv().get(TARGET_ENV_VAR);
        if (target != null) {
            return Pokemon.getById(target);
        }
        final List<String> enabledPokemonIds = Optional.ofNullable(PokemonProgressState.getInstance())
            .map(PokemonPicker::getEnabledPokemonNumbers)
            .orElse(null);
        if (enabledPokemonIds == null || enabledPokemonIds.isEmpty()) {
            return Pokemon.MISSINGNO;
        }
        return Pokemon.getById(enabledPokemonIds.get(RANDOM.nextInt(enabledPokemonIds.size())));
    }

    private static List<String> getEnabledPokemonNumbers(final PokemonProgressState state) {
        return state.pokemonNumbersEnabled.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toList());
    }

}
