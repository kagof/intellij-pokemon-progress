plugins {
    id("org.jetbrains.intellij") version "0.4.22"
    java
}

group = "com.kagof"
version = "1.7.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    testImplementation("com.sksamuel.scrimage", "scrimage-core", "4.0.22")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2021.1.3"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.getByName<org.jetbrains.intellij.tasks.PublishTask>("publishPlugin") {
    System.getenv("JETBRAINS_REPO_TOKEN")?.let { token(it) }
    System.getenv("PLUGIN_DEPLOYMENT_CHANNEL")?.let { channels(it) }
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    untilBuild(null)
    File("changenotes.html").let {
        if (it.exists() && it.isFile && it.canRead()) changeNotes(it.readText())
        else throw IllegalStateException("unable to read changenotes.html")
    }
    File("description.html").let {
        if (it.exists() && it.isFile && it.canRead()) pluginDescription(it.readText())
        else throw IllegalStateException("unable to read description.html")
    }

}