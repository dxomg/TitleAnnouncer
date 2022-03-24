import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    id("net.kyori.blossom") version "1.3.0"
    id("com.github.johnrengelman.shadow")
}

repositories {
    maven("https://libraries.minecraft.net")
    mavenCentral()
}

dependencies {
    shadow("org.spongepowered:configurate-hocon:4.1.2")
    compileOnly("net.kyori:adventure-api:4.10.1")
    compileOnly("net.kyori:adventure-text-minimessage:4.10.1")
    compileOnly("com.mojang:brigadier:1.0.18")
}

blossom{
    replaceToken("{version}", version)
    replaceTokenIn("src/main/java/me/dreamerzero/titleannouncer/common/PluginConstants.java")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }

    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        dependsOn(getByName("relocateShadowJar") as ConfigureShadowRelocation)
        minimize()
        archiveFileName.set("TitleAnnouncer.jar")
        configurations = listOf(project.configurations.shadow.get())
    }

    create<ConfigureShadowRelocation>("relocateShadowJar") {
        target = shadowJar.get()
        prefix = "me.dreamerzero.titleannouncer.libs"
    }
}