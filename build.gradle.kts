plugins {
    id("org.jetbrains.intellij") version "0.4.22"
    java
}

group = "com.kagof"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.3.5"
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      <ul>
      <li><b>1.0.0</b> Initial release</li>
      </ul>
      """)
}