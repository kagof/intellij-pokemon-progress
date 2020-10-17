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
* ![Butterfree (#12)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/butterfree.gif) Butterfree (#12) ![Butterfree (#12)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/butterfree_r.gif)
* ![Pikachu (#25)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu.gif) Pikachu (#25) ![Pikachu (#25)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu_r.gif)
* ![Nidoqueen (#31)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoqueen.gif) Nidoqueen (#31) ![Nidoqueen (#31)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoqueen_r.gif)
* ![Nidoking (#34)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoking.gif) Nidoking (#34) ![Nidoking (#34)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoking_r.gif)
* ![Jigglypuff (#39)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jigglypuff.gif) Jigglypuff (#39) ![Jigglypuff (#39)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jigglypuff_r.gif)
* ![Meowth (#52)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meowth.gif) Meowth (#52) ![Meowth (#52)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meowth_r.gif)
* ![Alakazam (#65)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/alakazam.gif) Alakazam (#65) ![Alakazam (#65)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/alakazam_r.gif)
* ![Machamp (#68)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/machamp.gif) Machamp (#68) ![Machamp (#68)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/machamp_r.gif)
* ![Slowpoke (#79)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/slowpoke.gif) Slowpoke (#79) ![Slowpoke (#79)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/slowpoke_r.gif)
* ![Magnemite (#81)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/magnemite.gif) Magnemite (#81) ![Magnemite (#81)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/magnemite_r.gif)
* ![Gengar (#94)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gengar.gif) Gengar (#94) ![Gengar (#94)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gengar_r.gif)
* ![Koffing (#109)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/koffing.gif) Koffing (#109) ![Koffing (#109)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/koffing_r.gif)
* ![Scyther (#123)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scyther.gif) Scyther (#123) ![Scyther (#123)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scyther_r.gif)
* ![Gyarados (#130)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gyarados.gif) Gyarados (#130) ![Gyarados (#130)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gyarados_r.gif)
* ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee.gif) Eevee (#133) ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee_r.gif)
* ![Snorlax (#143)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/snorlax.gif) Snorlax (#143) ![Snorlax (#143)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/snorlax_r.gif)
* ![Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/articuno.gif) Articuno (#144) ![Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/articuno_r.gif)
* ![Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zapdos.gif) Zapdos (#145) ![Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zapdos_r.gif)
* ![Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/moltres.gif) Moltres (#146) ![Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/moltres_r.gif)
* ![Dragonite (#149)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/dragonite.gif) Dragonite (#149) ![Dragonite (#149)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/dragonite_r.gif)
* ![Mewtwo (#150)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mewtwo.gif) Mewtwo (#150) ![Mewtwo (#150)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mewtwo_r.gif)
* ![Mew (#151)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mew.gif) Mew (#151) ![Mew (#151)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mew_r.gif)

### Generation II

* ![Chikorita (#152)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chikorita.gif) Chikorita (#152) ![Chikorita (#152)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chikorita_r.gif)
* ![Meganium (#154)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meganium.gif) Meganium (#154) ![Meganium (#154)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meganium_r.gif)
* ![Cyndaquil (#155)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cyndaquil.gif) Cyndaquil (#155) ![Cyndaquil (#155)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cyndaquil_r.gif)
* ![Typhlosion (#157)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/typhlosion.gif) Typhlosion (#157) ![Typhlosion (#157)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/typhlosion_r.gif)
* ![Totodile (#158)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/totodile.gif) Totodile (#158) ![Totodile (#158)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/totodile_r.gif)
* ![Feraligatr (#160)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/feraligatr.gif) Feraligatr (#160) ![Feraligatr (#160)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/feraligatr_r.gif)
* ![Togepi (#175)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/togepi.gif) Togepi (#175) ![Togepi (#175)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/togepi_r.gif)
* ![Wobbuffet (#202)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wobbuffet.gif) Wobbuffet (#202) ![Wobbuffet (#202)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wobbuffet_r.gif)
* ![Raikou (#243)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/raikou.gif) Raikou (#243) ![Raikou (#243)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/raikou_r.gif)
* ![Entei (#244)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/entei.gif) Entei (#244) ![Entei (#244)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/entei_r.gif)
* ![Suicune (#245)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/suicune.gif) Suicune (#245) ![Suicune (#245)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/suicune_r.gif)
* ![Lugia (#249)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/lugia.gif) Lugia (#249) ![Lugia (#249)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/lugia_r.gif)
* ![Ho-oh (#250)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/ho-Oh.gif) Ho-oh (#250) ![Ho-Oh (#250)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/ho-Oh_r.gif)
* ![Celebi (#251)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/celebi.gif) Celebi (#251) ![Celebi (#251)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/celebi_r.gif)

### Generation III

* ![Wailmer (#320)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailmer.gif) Wailmer (#320) ![Wailmer (#320)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailmer_r.gif)
* ![Wailord (#321)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailord.gif) Wailord (#321) ![Wailord (#321)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailord_r.gif)

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

## Contributing

Contributions are very welcome on this project! Please see our [contributing guidelines](CONTRIBUTING.md) and [code of conduct](CODE_OF_CONDUCT.md) to get started.

## Acknowledgements

### Sprites

* [Pokencyclopedia SpriteDex - Heart Gold & Soul Silver](https://www.pokencyclopedia.info/en/index.php?id=sprites/overworlds/o-r_hgss)
    * Bulbasaur, Venusaur, Charmander, Charizard, Squirtle, Blastoise, Butterfree, Pikachu, Nidoqueen, Nidoking, Jigglypuff, Meowth, Alakazam, Machamp, Slowpoke, Magnemite, Gengar, Koffing, Scyther, Gyarados, Eevee, Snorlax, Articuno, Zapdos, Moltres, Dragonite, Mewtwo, Mew
    * Chikorita, Meganium, Cyndaquil, Typhlosion, Totodile, Feraligatr, Togepi, Wobbuffet
    * Wailmer, Wailord
    * MissingNo.
* [Bulbagarden Archives](https://archives.bulbagarden.net)
    * Mimikyu (first frame)
* [SageDeoxys](https://www.pokecommunity.com/showthread.php?t=429414)
    * Grookey, Rillaboom, Scorbunny, Cinderace, Sobble, Intelleon, Wooloo, Zacian, Zamazenta

### Code

* The code for the progress bar itself was adapted from [Nyan Progess Bar](https://github.com/batya239/NyanProgressBar).
* This plugin is of course heavily dependent on JetBrains' IntelliJ SDK  

### Misc

* Gif editing done with [ImageMagick](https://imagemagick.org/script/index.php) (using [this](./editSprite.sh) script)
* Types, names, numbers, & info mainly gathered from [Bulbapedia](https://bulbapedia.bulbagarden.net)
* Type colours taken from [Bulbapedia's Type color templates](https://bulbapedia.bulbagarden.net/wiki/Category:Type_color_templates)
* The idea for this plugin came from [KikiManjaro's Pokemon Trainer Progress Bar](https://plugins.jetbrains.com/plugin/14609-pokemon-trainer-progress-bar)
* The Pokémon Company, for creating Pokémon