# IntelliJ Pokémon Progress Bar

[![Build](https://img.shields.io/github/actions/workflow/status/kagof/intellij-pokemon-progress/verify.yml?branch=master)](https://gist.github.com/kagof/63edd71468e771dcde77ff87f251f8a3)
[![Version](https://img.shields.io/jetbrains/plugin/v/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/versions)
[![Rating](https://img.shields.io/jetbrains/plugin/r/stars/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/reviews)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/15090-pokemon-progress)](https://plugins.jetbrains.com/plugin/15090-pokemon-progress)
[![Stars](https://img.shields.io/github/stars/kagof/intellij-pokemon-progress?style=social)](https://github.com/kagof/intellij-pokemon-progress)

[![Example](eg/example.gif)](eg/example.gif)

[![All](eg/family.gif)](eg/family.gif)

This is [a plugin](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/versions) for [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/) (And other compatible IDEs), which replaces your progress bars with a (random) Pokémon. The color of the progress bar fill is based on that Pokémon's type(s). Preferences are located under `Preferences > Appearance & Behaviour > Pokémon Progress`.

If you like the plugin, please consider [rating it on the Marketplace](https://plugins.jetbrains.com/plugin/15090-pokemon-progress/reviews) or [starring it on Github](https://github.com/kagof/intellij-pokemon-progress)!

## Included Pokémon

### Generation I

* ![Bulbasaur (#001)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/bulbasaur.gif) Bulbasaur (#001) ![Bulbasaur (#001)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/bulbasaur_r.gif)
* ![Venusaur (#003)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/venusaur.gif) Venusaur (#003) ![Venusaur (#003)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/venusaur_r.gif)
* ![Charmander (#004)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charmander.gif) Charmander (#004) ![Charmander (#004)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charmander_r.gif)
* ![Charizard (#006)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charizard.gif) Charizard (#006) ![Charizard (#006)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/charizard_r.gif)
* ![Squirtle (#007)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/squirtle.gif) Squirtle (#007) ![Squirtle (#007)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/squirtle_r.gif)
* ![Blastoise (#009)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blastoise.gif) Blastoise (#009) ![Blastoise (#009)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blastoise_r.gif)
* ![Butterfree (#012)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/butterfree.gif) Butterfree (#012) ![Butterfree (#012)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/butterfree_r.gif)
* ![Pikachu (#025)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu.gif) Pikachu (#025) ![Pikachu (#025)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/pikachu_r.gif)
* ![Nidoqueen (#031)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoqueen.gif) Nidoqueen (#031) ![Nidoqueen (#031)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoqueen_r.gif)
* ![Nidoking (#034)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoking.gif) Nidoking (#034) ![Nidoking (#034)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/nidoking_r.gif)
* ![Jigglypuff (#039)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jigglypuff.gif) Jigglypuff (#039) ![Jigglypuff (#039)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jigglypuff_r.gif)
* ![Diglett (#050)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/diglett.gif) Diglett (#050) ![Diglett (#050)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/diglett_r.gif)
* ![Dugtrio (#051)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/dugtrio.gif) Dugtrio (#051) ![Dugtrio (#051)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/dugtrio_r.gif)
* ![Meowth (#052)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meowth.gif) Meowth (#052) ![Meowth (#052)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/meowth_r.gif)
* ![Psyduck (#054)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/psyduck.gif) Psyduck (#054) ![Psyduck (#054)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/psyduck_r.gif)
* ![Golduck (#055)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/golduck.gif) Golduck (#055) ![Golduck (#055)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/golduck_r.gif)
* ![Alakazam (#065)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/alakazam.gif) Alakazam (#065) ![Alakazam (#065)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/alakazam_r.gif)
* ![Machamp (#068)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/machamp.gif) Machamp (#068) ![Machamp (#068)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/machamp_r.gif)
* ![Slowpoke (#079)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/slowpoke.gif) Slowpoke (#079) ![Slowpoke (#079)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/slowpoke_r.gif)
* ![Magnemite (#081)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/magnemite.gif) Magnemite (#081) ![Magnemite (#081)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/magnemite_r.gif)
* ![Gengar (#094)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gengar.gif) Gengar (#094) ![Gengar (#094)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gengar_r.gif)
* ![Koffing (#109)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/koffing.gif) Koffing (#109) ![Koffing (#109)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/koffing_r.gif)
* ![Scyther (#123)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scyther.gif) Scyther (#123) ![Scyther (#123)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scyther_r.gif)
* ![Gyarados (#130)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gyarados.gif) Gyarados (#130) ![Gyarados (#130)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/gyarados_r.gif)
* ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee.gif) Eevee (#133) ![Eevee (#133)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/eevee_r.gif)
* ![Vaporeon (#134)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/vaporeon.gif) Vaporeon (#134) ![Vaporeon (#134)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/vaporeon_r.gif)
* ![Jolteon (#135)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jolteon.gif) Jolteon (#135) ![Jolteon (#135)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jolteon_r.gif)
* ![Flareon (#136)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/flareon.gif) Flareon (#136) ![Flareon (#136)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/flareon_r.gif)
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
* ![Espeon (#196)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/espeon.gif) Espeon (#196) ![Espeon (#196)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/espeon_r.gif)
* ![Umbreon (#197)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/umbreon.gif) Umbreon (#197) ![Umbreon (#197)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/umbreon_r.gif)
* ![Wobbuffet (#202)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wobbuffet.gif) Wobbuffet (#202) ![Wobbuffet (#202)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wobbuffet_r.gif)
* ![Raikou (#243)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/raikou.gif) Raikou (#243) ![Raikou (#243)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/raikou_r.gif)
* ![Entei (#244)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/entei.gif) Entei (#244) ![Entei (#244)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/entei_r.gif)
* ![Suicune (#245)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/suicune.gif) Suicune (#245) ![Suicune (#245)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/suicune_r.gif)
* ![Lugia (#249)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/lugia.gif) Lugia (#249) ![Lugia (#249)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/lugia_r.gif)
* ![Ho-Oh (#250)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/ho-Oh.gif) Ho-Oh (#250) ![Ho-Oh (#250)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/ho-Oh_r.gif)
* ![Celebi (#251)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/celebi.gif) Celebi (#251) ![Celebi (#251)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/celebi_r.gif)

### Generation III

* ![Treecko (#252)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/treecko.gif) Treecko (#252) ![Treecko (#252)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/treecko_r.gif)
* ![Sceptile (#254)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sceptile.gif) Sceptile (#254) ![Sceptile (#254)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sceptile_r.gif)
* ![Torchic (#255)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/torchic.gif) Torchic (#255) ![Torchic (#255)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/torchic_r.gif)
* ![Blaziken (#257)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blaziken.gif) Blaziken (#257) ![Blaziken (#257)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/blaziken_r.gif)
* ![Mudkip (#258)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mudkip.gif) Mudkip (#258) ![Mudkip (#258)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mudkip_r.gif)
* ![Swampert (#260)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/swampert.gif) Swampert (#260) ![Swampert (#260)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/swampert_r.gif)
* ![Wailmer (#320)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailmer.gif) Wailmer (#320) ![Wailmer (#320)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailmer_r.gif)
* ![Wailord (#321)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailord.gif) Wailord (#321) ![Wailord (#321)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wailord_r.gif)
* ![Latias (#380)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/latias.gif) Latias (#380) ![Latias (#380)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/latias_r.gif)
* ![Latios (#381)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/latios.gif) Latios (#381) ![Latios (#381)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/latios_r.gif)
* ![Kyogre (#382)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/kyogre.gif) Kyogre (#382) ![Kyogre (#382)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/kyogre_r.gif)
* ![Groudon (#383)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/groudon.gif) Groudon (#383) ![Groudon (#383)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/groudon_r.gif)
* ![Rayquaza (#384)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rayquaza.gif) Rayquaza (#384) ![Rayquaza (#384)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rayquaza_r.gif)
* ![Jirachi (#385)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jirachi.gif) Jirachi (#385) ![Jirachi (#385)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/jirachi_r.gif)
* ![Deoxys (#386)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/deoxys.gif) Deoxys (#386) ![Deoxys (#386)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/deoxys_r.gif)

### Generation IV

* ![Turtwig (#387)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/turtwig.gif) Turtwig (#387) ![Turtwig (#387)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/turtwig_r.gif)
* ![Torterra (#389)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/torterra.gif) Torterra (#389) ![Torterra (#389)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/torterra_r.gif)
* ![Chimchar (#390)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chimchar.gif) Chimchar (#390) ![Chimchar (#390)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chimchar_r.gif)
* ![Infernape (#392)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/infernape.gif) Infernape (#392) ![Infernape (#392)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/infernape_r.gif)
* ![Piplup (#393)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/piplup.gif) Piplup (#393) ![Piplup (#393)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/piplup_r.gif)
* ![Empoleon (#395)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/empoleon.gif) Empoleon (#395) ![Empoleon (#395)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/empoleon_r.gif)
* ![Leafeon (#470)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/leafeon.gif) Leafeon (#470) ![Leafeon (#470)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/leafeon_r.gif)
* ![Glaceon (#471)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/glaceon.gif) Glaceon (#471) ![Glaceon (#471)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/glaceon_r.gif)
* ![Arceus (#493)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/arceus.gif) Arceus (#493) ![Arceus (#493)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/arceus_r.gif)

### Generation V

* ![Snivy (#495)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/snivy.gif) Snivy (#495) ![Snivy (#495)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/snivy_r.gif)
* ![Serperior (#497)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/serperior.gif) Serperior (#497) ![Serperior (#497)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/serperior_r.gif)
* ![Tepig (#498)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/tepig.gif) Tepig (#498) ![Tepig (#498)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/tepig_r.gif)
* ![Emboar (#500)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/emboar.gif) Emboar (#500) ![Emboar (#500)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/emboar_r.gif)
* ![Oshawott (#501)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/oshawott.gif) Oshawott (#501) ![Oshawott (#501)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/oshawott_r.gif)
* ![Samurott (#503)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/samurott.gif) Samurott (#503) ![Samurott (#503)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/samurott_r.gif)

### Generation VI

* ![Chespin (#650)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chespin.gif) Chespin (#650) ![Chespin (#650)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chespin_r.gif)
* ![Chesnaught (#652)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chesnaught.gif) Chesnaught (#652) ![Chesnaught (#652)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/chesnaught_r.gif)
* ![Fennekin (#653)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/fennekin.gif) Fennekin (#653) ![Fennekin (#653)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/fennekin_r.gif)
* ![Delphox (#655)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/delphox.gif) Delphox (#655) ![Delphox (#655)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/delphox_r.gif)
* ![Froakie (#656)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/froakie.gif) Froakie (#656) ![Froakie (#656)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/froakie_r.gif)
* ![Greninja (#658)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/greninja.gif) Greninja (#658) ![Greninja (#658)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/greninja_r.gif)
* ![Sylveon (#700)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sylveon.gif) Sylveon (#700) ![Sylveon (#700)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sylveon_r.gif)

### Generation VII

* ![Rowlet (#722)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rowlet.gif) Rowlet (#722) ![Rowlet (#722)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rowlet_r.gif)
* ![Decidueye (#724)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/decidueye.gif) Decidueye (#724) ![Decidueye (#724)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/decidueye_r.gif)
* ![Litten (#725)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/litten.gif) Litten (#725) ![Litten (#725)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/litten_r.gif)
* ![Incineroar (#727)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/incineroar.gif) Incineroar (#727) ![Incineroar (#727)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/incineroar_r.gif)
* ![Popplio (#728)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/popplio.gif) Popplio (#728) ![Popplio (#728)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/popplio_r.gif)
* ![Primarina (#730)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/primarina.gif) Primarina (#730) ![Primarina (#730)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/primarina_r.gif)
* ![Mimikyu (#778)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mimikyu.gif) Mimikyu (#778) ![Mimikyu (#778)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/mimikyu_r.gif)

### Generation VIII

* ![Galarian Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_articuno.gif) Galarian Articuno (#144) ![Galarian Articuno (#144)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_articuno_r.gif)
* ![Galarian Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_zapdos.gif) Galarian Zapdos (#145) ![Galarian Zapdos (#145)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_zapdos_r.gif)
* ![Galarian Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_moltres.gif) Galarian Moltres (#146) ![Galarian Moltres (#146)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/galarian_moltres_r.gif)
* ![Grookey (#810)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/grookey.gif) Grookey (#810) ![Grookey (#810)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/grookey_r.gif)
* ![Rillaboom (#812)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rillaboom.gif) Rillaboom (#812) ![Rillaboom (#812)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/rillaboom_r.gif)
* ![Scorbunny (#813)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scorbunny.gif) Scorbunny (#813) ![Scorbunny (#813)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/scorbunny_r.gif)
* ![Cinderace (#815)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cinderace.gif) Cinderace (#815) ![Cinderace (#815)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cinderace_r.gif)
* ![Sobble (#816)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sobble.gif) Sobble (#816) ![Sobble (#816)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sobble_r.gif)
* ![Intelleon (#818)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/intelleon.gif) Intelleon (#818) ![Intelleon (#818)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/intelleon_r.gif)
* ![Wooloo (#831)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wooloo.gif) Wooloo (#831) ![Wooloo (#831)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/wooloo_r.gif)
* ![Cramorant (#845)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cramorant.gif) Cramorant (#845) ![Cramorant (#845)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/cramorant_r.gif)
* ![Zacian (#888)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zacian.gif) Zacian (#888) ![Zacian (#888)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zacian_r.gif)
* ![Zamazenta (#889)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zamazenta.gif) Zamazenta (#889) ![Zamazenta (#889)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/zamazenta_r.gif)

### Generation IX

* ![Sprigatito (#906)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sprigatito.gif) Sprigatito (#906) ![Sprigatito (#906)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/sprigatito_r.gif)
* ![Fuecoco (#909)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/fuecoco.gif) Fuecoco (#909) ![Fuecoco (#909)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/fuecoco_r.gif)
* ![Quaxly (#912)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/quaxly.gif) Quaxly (#912) ![Quaxly (#912)](src/main/resources/com/kagof/intellij/plugins/pokeprogress/sprites/quaxly_r.gif)

[comment]: <> (end-included-pokemon)

## Contributing

Contributions are very welcome on this project! Contributions can take the form of bug reports, feature requests, pull requests, Pokémon requests or more! Please see our [contributing guidelines](CONTRIBUTING.md) and [code of conduct](CODE_OF_CONDUCT.md) to get started.

## Acknowledgements

### Sprites

* [Pokencyclopedia SpriteDex - Heart Gold & Soul Silver](https://www.pokencyclopedia.info/en/index.php?id=sprites/overworlds/o-r_hgss)
  * All Gen I-IV sprites unless otherwise noted
  * MissingNo.
* [Kyle-Dove on DeviantArt](https://www.deviantart.com/kyle-dove/gallery)
  * Snivy, Tepig, Oshawott
* [cSc-A7X on DeviantArt](https://www.deviantart.com/csc-a7x/gallery)
  * Serperior, Emboar, Samurott
* [princess-phoenix on DeviantArt](https://www.deviantart.com/princess-phoenix/art/Gen-6-Kalos-Pokemon-Overworld-Sprites-525954409)
  * All Gen VI sprites unless otherwise noted
* [JuJoAura on DeviantArt](https://www.deviantart.com/jujoaura/art/Sylveon-Full-Sprite-379989482)
  * Sylveon
* [Princess-Phoenix, Larryturbo, Kiddkatt, Zender1752, SageDeoxys, curated by Larryturbo](https://www.deviantart.com/larryturbo/art/Gen-7-Alola-Overworld-Sprites-805455576)
  * All Gen VII sprites unless otherwise noted
* [SageDeoxys](https://www.pokecommunity.com/showthread.php?t=429414)
  * All Gen VIII sprites unless otherwise noted
* [DarkusShadow on DeviantArt](https://www.deviantart.com/darkusshadow)
  * All Gen IX sprites unless otherwise noted

### Code

* The code for the progress bar itself was adapted from [Nyan Progess Bar](https://github.com/batya239/NyanProgressBar).
* This plugin is of course heavily dependent on JetBrains' IntelliJ SDK
* All the [contributors](https://github.com/kagof/intellij-pokemon-progress/graphs/contributors) who've helped build this plugin

### Colours

* Official scheme colours taken from [Bulbapedia's Type color templates](https://bulbapedia.bulbagarden.net/wiki/Category:Type_color_templates)
* Artemis251 scheme colours taken from [Artemis251's Pokémon Emerald Randomizer Type Color Guide](http://artemis251.fobby.net/downloads/emerald/)
* Nyjee scheme colours taken from [Nyjee's Pokémon Type Colors on DeviantArt](https://www.deviantart.com/nyjee/art/pokemon-type-colors-807671821)

### Misc

* Sprite Gif editing done with [ImageMagick](https://imagemagick.org/script/index.php) (using [this](./editSprite.sh) script)
* Types, names, numbers, & info mainly gathered from [Bulbapedia](https://bulbapedia.bulbagarden.net)
* The idea for this plugin came from [KikiManjaro's Pokemon Trainer Progress Bar](https://plugins.jetbrains.com/plugin/14609-pokemon-trainer-progress-bar)
* Family photo generated using [Scrimage](https://github.com/sksamuel/scrimage) for GIF reading & writing
* The Pokémon Company, for creating Pokémon
