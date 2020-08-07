pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version extra["kotlin.version"] as String
        kotlin("plugin.jpa") version extra["kotlin.version"] as String
        kotlin("plugin.allopen") version extra["kotlin.version"] as String
        id("io.quarkus") version extra["quarkus.plugin.version"] as String
    }
}

rootProject.name = "trader-forex"

include("forex-domain", "forex-core", "forex-service")