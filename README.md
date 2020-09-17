# IntelliJ Pokémon Progress Bar
[![Build](https://img.shields.io/github/workflow/status/kagof/intellij-pokemon-progress/Java%20CI%20with%20Gradle/master)](https://gist.github.com/kagof/63edd71468e771dcde77ff87f251f8a3)
[![Version](https://img.shields.io/jetbrains/plugin/v/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/versions)
[![Rating](https://img.shields.io/jetbrains/plugin/r/rating/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/reviews)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress)

![](eg/example.gif)

This is a plugin for [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/), which replaces your progress bars with a (random) Pokémon. The color of the progress bar fill is based on that Pokémon's type(s).

## Included Pokémon

### Generation I

* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/bulbasaur.gif) #1 Bulbasaur
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/venusaur.gif) #3 Venusaur
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charmander.gif) #4 Charmander
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charizard.gif) #6 Charizard
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/squirtle.gif) #7 Squirtle
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blastoise.gif) #9 Blastoise
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu.gif) #25 Pikachu
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee.gif) #133 Eevee

### Generation VII

* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mimikyu.gif) #778 Mimikyu

### Generation VIII

* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/grookey.gif) #810 Grookey
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rillaboom.gif) #812 Rillaboom
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scorbunny.gif) #813 Scorbunny
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cinderace.gif) #815 Cinderace
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sobble.gif) #816 Sobble
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/intelleon.gif) #818 Intelleon
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wooloo.gif) #831 Wooloo
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zacian.gif) #888 Zacian
* ![](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zamazenta.gif) #889 Zamazenta

## Acknowledgements

### Sprites

* [Pokencyclopedia SpriteDex - Heart Gold & Soul Silver](https://www.pokencyclopedia.info/en/index.php?id=sprites/overworlds/o-r_hgss)
    * Blastoise, Bulbasaur, Charizard, Charmander, Pikachu, Squirtle, Venusaur
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