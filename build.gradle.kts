plugins {
    id("org.jetbrains.intellij") version "0.4.22"
    java
}

group = "com.kagof"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2020.2.1"
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
    <ul>
        <li><b><a href="https://github.com/kagof/intellij-pokemon-progress/releases/tag/1.1.0">1.1.0</a></b>
            <ul>
                <li>New Gen I: Butterfree, Meowth, Slowpoke, Gengar, Koffing, Gyarados, Snorlax, Articuno, Zapdos, Moltres, Mewtwo, Mew</li>
                <li>New Gen II: Togepi, Wobbuffet</li>
                <li>New Gen III: Wailmer, Wailord</li>
                <li>New Gen VIII: Grookey, Rillaboom, Scorbunny, Cinderace, Sobble, Intelleon, Wooloo, Zacian, Zamazenta</li>
                <li>Lazy loads Pokémon icons</li>
                <li>Plugin is now dynamically reloadable (uses Listeners instead of Component)</li>
            </ul>
        </li>
        <li><b><a href="https://github.com/kagof/intellij-pokemon-progress/releases/tag/1.0.2">1.0.2</a></b> 
            <ul>
                <li>fixes icon compatibility with Retina screens</li>
            </ul>
        </li>
        <li><b><a href="https://github.com/kagof/intellij-pokemon-progress/releases/tag/1.0.1">1.0.1</a></b>
            <ul>
                <li>backwards compatibility with IntelliJ 2019.3.5</li>
                <li>fixes display issue when progress bar goes from indeterminate to determinate</li>
            </ul>
        </li>
        <li><b><a href="https://github.com/kagof/intellij-pokemon-progress/releases/tag/1.0.0">1.0.0</a></b>
            <ul>
                <li>Initial release</li>
                <li>New Gen I: Bulbasaur, Venusaur, Charmander, Charizard, Squirtle, Blastoise, Pikachu, Eevee</li>
                <li>New Gen VII: Mimikyu</li>
            </ul>
        </li>
    </ul>
      """)
}