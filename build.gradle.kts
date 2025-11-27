import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    kotlin("jvm") version "2.+"
    id("xyz.jpenilla.run-paper") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("net.minecrell.plugin-yml.paper") version "0.6.+"
    id("com.gradleup.shadow") version "8.+"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.extendedclip.com/releases/")
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}


group = properties["group"] as String
version = properties["version"] as String
description = properties["description"] as String

val gameVersion by properties
val projectName = properties["name"] as String
val pluginMain = properties["main"] as String


dependencies {
    compileOnly("io.papermc.paper:paper-api:${gameVersion}-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.+")


    implementation("net.kyori", "adventure-text-minimessage", "4.+")

    implementation("dev.jorel:commandapi-kotlin-paper:11.0.0")
    compileOnly("dev.jorel:commandapi-paper-core:11.0.0")
    implementation("dev.jorel:commandapi-paper-shade:11.0.0")

    compileOnly("me.clip:placeholderapi:2.11.6")

}

kotlin {
    jvmToolchain(21)
}

tasks {
    runServer {
        minecraftVersion(gameVersion as String)
    }
    assemble {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    compileKotlin {

    }
}

tasks.jar {
    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }
}

paper {
    main = pluginMain
    apiVersion = "1.21"
    website = "https://modrinth.com/plugin/xcolor"
    authors = listOf("xyzjesper")
    // Optionals
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    serverDependencies {
        register("PlaceholderAPI") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }
}