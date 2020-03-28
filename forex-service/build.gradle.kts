plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("com.google.cloud.tools.jib")
    jacoco
}

configurations.testImplementation {
    exclude(group = "org.junit.vintage")
    exclude(group = "org.mockito")
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlin.coroutines.version")}")

    // Project
    implementation(project(":forex-core"))
    implementation(project(":forex-domain"))

    // Frameworks
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")

    // Tests
    testImplementation("com.ninja-squad:springmockk:2.0.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    // Development tools
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

jib {
    from {
        image = "openjdk:13-jdk-alpine"
    }
    to {
        image = "kunimido/trader-${project.name}"
    }
    container {
        ports = listOf("8080")
    }
}