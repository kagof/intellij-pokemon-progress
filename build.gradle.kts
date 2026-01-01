import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.intellij.platform") version "2.10.5"
    java
    kotlin("jvm")
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        create(properties("pluginIdeaType"), properties("pluginIdeaVersion"))
        zipSigner()
        pluginVerifier(properties("pluginVerifierVersion"))
        testFramework(TestFrameworkType.Platform)
    }
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.sksamuel.scrimage", "scrimage-core", "4.3.5")
    testImplementation(kotlin("test"))
}

intellijPlatform {
    buildSearchableOptions = true
    instrumentCode = true
    autoReload = true
    projectName = project.name
    pluginConfiguration {
        name = properties("pluginName")
        version = properties("pluginVersion")
        id = properties("pluginId")
        File(properties("changenotesFile")).let {
            if (it.exists() && it.isFile && it.canRead()) changeNotes.set(it.readText())
            else throw IllegalStateException("unable to read ${it.name}")
        }
        File(properties("descriptionFile")).let {
            if (it.exists() && it.isFile && it.canRead()) description.set(it.readText())
            else throw IllegalStateException("unable to read ${it.name}")
        }
        ideaVersion {
            sinceBuild = properties("pluginSinceBuild")
            untilBuild = provider { null }
        }
    }
    publishing {
        System.getenv("JETBRAINS_REPO_TOKEN")?.let { token.set(it) }
        System.getenv("PLUGIN_DEPLOYMENT_CHANNELS")
            ?.let { channels.set(it.split(",").map { s -> s.trim() }.toList()) }
            ?: channels.set(listOf("default"))
    }
    signing {
        System.getenv("JETBRAINS_REPO_SIGNING_KEY")?.let { privateKey.set(it) }
        System.getenv("JETBRAINS_REPO_SIGNING_KEY_PASSWORD")?.let { password.set(it) }
        System.getenv("JETBRAINS_REPO_CERTIFICATE_CHAIN")?.let { certificateChain.set(it) }
    }
    pluginVerification {
        ides {
            properties("pluginVerifierIdeVersions")
                .split(",")
                .map { it.trim() }
                .map { it.split("-", limit = 2) }
                .filter { it.size == 2 }
                .forEach { create(it[0], it[1]) }
        }
        failureLevel.set(
            listOf(
                VerifyPluginTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                VerifyPluginTask.FailureLevel.NOT_DYNAMIC
            )
        )
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

tasks {
    register("indexColors", DefaultTask::class) {
        group = "pokemon-progress"
        description = "create index file for color schemes"
        doFirst {
            File("src/main/resources/com/kagof/intellij/plugins/pokeprogress/colors").let { dir ->
                if (dir.exists() && dir.isDirectory && dir.canRead()) {
                    val strb = StringBuilder()
                    dir.listFiles()
                        ?.filter { it.isFile }
                        ?.filter { it.name.endsWith(".csv") }
                        ?.forEach { strb.append(it.name).append("\n") }
                    val index = File(dir, ".cscheme.index")
                    index.delete()
                    index.createNewFile()
                    index.writeText(strb.toString())
                } else throw IllegalStateException("unable to read color schemes")
            }
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        dependsOn("indexColors")
    }

}