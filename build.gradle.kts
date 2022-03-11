import org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel

val name: String by project
val changenotesFile: String by project
val descriptionFile: String by project
val ideaVersion: String by project
val javaVersion: String by project
val pluginVerifierIdeVersions: String by project

plugins {
    id("org.jetbrains.intellij") version "1.4.0"
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    testImplementation("com.sksamuel.scrimage", "scrimage-core", "4.0.22")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set(ideaVersion)
    pluginName.set(name)
    downloadSources.set(true)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    register("genDocs", JavaExec::class) {
        group = "pokemon-progress"
        description = "generate documentation"
        classpath = java.sourceSets["test"].runtimeClasspath
        mainClass.set("com.kagof.intellij.plugins.pokeprogress.DocumentationGenerator")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        untilBuild.set(null as String?)
        File(changenotesFile).let {
            if (it.exists() && it.isFile && it.canRead()) changeNotes.set(it.readText())
            else throw IllegalStateException("unable to read $changenotesFile")
        }
        File(descriptionFile).let {
            if (it.exists() && it.isFile && it.canRead()) pluginDescription.set(it.readText())
            else throw IllegalStateException("unable to read $descriptionFile")
        }
    }

    runPluginVerifier {
        ideVersions.set(pluginVerifierIdeVersions.split(",").map { it.trim() }.toList())
        failureLevel.set(listOf(FailureLevel.COMPATIBILITY_PROBLEMS,
                FailureLevel.NOT_DYNAMIC))
    }

    signPlugin {
        System.getenv("JETBRAINS_REPO_SIGNING_KEY")?.let { privateKey.set(it) }
        System.getenv("JETBRAINS_REPO_SIGNING_KEY_PASSWORD")?.let { password.set(it) }
        System.getenv("JETBRAINS_REPO_CERTIFICATE_CHAIN")?.let { certificateChain.set(it) }
    }

    publishPlugin {
        System.getenv("JETBRAINS_REPO_TOKEN")?.let { token.set(it) }
        System.getenv("PLUGIN_DEPLOYMENT_CHANNELS")?.let { channels.set(it.split(",").map { s -> s.trim() }.toList()) }
    }
}