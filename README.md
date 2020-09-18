# IntelliJ Pokémon Progress Bar
[![Build](https://img.shields.io/github/workflow/status/kagof/intellij-pokemon-progress/Java%20CI%20with%20Gradle/master)](https://gist.github.com/kagof/63edd71468e771dcde77ff87f251f8a3)
[![Version](https://img.shields.io/jetbrains/plugin/v/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/versions)
[![Rating](https://img.shields.io/jetbrains/plugin/r/rating/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/reviews)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress)

![](eg/example.gif)

This is [a plugin](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/versions) for [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/), which replaces your progress bars with a (random) Pokémon. The color of the progress bar fill is based on that Pokémon's type(s).

## Included Pokémon

### Generation I

* ![Bulbasaur (#1)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/bulbasaur.gif) Bulbasaur (#1) ![Bulbasaur (#1)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/bulbasaur_r.gif)
* ![Venusaur (#3)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/venusaur.gif) Venusaur (#3) ![Venusaur (#3)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/venusaur_r.gif)
* ![Charmander (#4)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charmander.gif) Charmander (#4) ![Charmander (#4)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charmander_r.gif)
* ![Charizard (#6)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charizard.gif) Charizard (#6) ![Charizard (#6)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charizard_r.gif)
* ![Squirtle (#7)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/squirtle.gif) Squirtle (#7) ![Squirtle (#7)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/squirtle_r.gif)
* ![Blastoise (#9)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blastoise.gif) Blastoise (#9) ![Blastoise (#9)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blastoise_r.gif)
* ![Pikachu (#25)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu.gif) Pikachu (#25) ![Pikachu (#25)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu_r.gif)
* ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee.gif) Eevee (#133) ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee_r.gif)
* ![Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/articuno.gif) Articuno (#144) ![Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/articuno_r.gif)
* ![Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zapdos.gif) Zapdos (#145) ![Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zapdos_r.gif)
* ![Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/moltres.gif) Moltres (#146) ![Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/moltres_r.gif)
* ![Mewtwo (#150)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mewtwo.gif) Mewtwo (#150) ![Mewtwo (#150)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mewtwo_r.gif)
* ![Mew (#151)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mew.gif) Mew (#151) ![Mew (#151)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mew_r.gif)

### Generation VII

* ![Mimikyu (#778)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mimikyu.gif) Mimikyu (#778) ![Mimikyu (#778)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mimikyu_r.gif)

### Generation VIII

* ![Grookey (#810)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/grookey.gif) Grookey (#810) ![Grookey (#810)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/grookey_r.gif)
* ![Rillaboom (#812)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rillaboom.gif) Rillaboom (#812) ![Rillaboom (#812)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rillaboom_r.gif)
* ![Scorbunny (#813)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scorbunny.gif) Scorbunny (#813) ![Scorbunny (#813)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scorbunny_r.gif)
* ![Cinderace (#815)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cinderace.gif) Cinderace (#815) ![Cinderace (#815)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cinderace_r.gif)
* ![Sobble (#816)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sobble.gif) Sobble (#816) ![Sobble (#816)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sobble_r.gif)
* ![Intelleon (#818)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/intelleon.gif) Intelleon (#818) ![Intelleon (#818)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/intelleon_r.gif)
* ![Wooloo (#831)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wooloo.gif) Wooloo (#831) ![Wooloo (#831)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wooloo_r.gif)
* ![Zacian (#888)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zacian.gif) Zacian (#888) ![Zacian (#888)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zacian_r.gif)
* ![Zamazenta (#889)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zamazenta.gif) Zamazenta (#889) ![Zamazenta (#889)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zamazenta_r.gif)

## Acknowledgements

### Sprites

* [Pokencyclopedia SpriteDex - Heart Gold & Soul Silver](https://www.pokencyclopedia.info/en/index.php?id=sprites/overworlds/o-r_hgss)
    * Bulbasaur, Venusaur, Charmander, Charizard, Squirtle, Blastoise, Pikachu, Eevee, Articuno, Zapdos, Moltres, Mewtwo, Mew
* [Bulbagarden Archives](https://archives.bulbagarden.net)
    * Mimikyu (first frame)
* [SageDeoxys](https://www.pokecommunity.com/showthread.php?t=429414)
    * Grookey, Rillaboom, Scorbunny, Cinderace, Sobble, Intelleon, Wooloo, Zacian, Zamazenta

### Code

* Much of the UI code (as well as the idea) was adapted from [Nyan Progess Bar](https://github.com/batya239/NyanProgressBar).
* This plugin is of course heavily dependent on JetBrains' IntelliJ SDK  

### Misc

* Gif editing done with [ImageMagick](https://imagemagick.org/script/index.php) (using [this](./editSprite.sh) script)
* Types, names, numbers, & info mainly gathered from [Bulbapedia](https://bulbapedia.bulbagarden.net)
* Type colours taken from [Bulbapedia's Type color templates](https://bulbapedia.bulbagarden.net/wiki/Category:Type_color_templates)
* The Pokémon Company, for creating Pokémon