pluginManagement {
    plugins {
        kotlin("jvm") version extra["kotlin.version"] as String
        kotlin("plugin.spring") version extra["kotlin.version"] as String
        id("io.spring.dependency-management") version extra["spring.dependency-management.version"] as String
        id("org.springframework.boot") version extra["spring.boot.version"] as String
        id("com.google.cloud.tools.jib") version extra["google.jib.version"] as String
    }
}

rootProject.name = "trader-forex"

include("forex-domain", "forex-core", "forex-service")