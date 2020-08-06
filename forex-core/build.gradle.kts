plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    // Project
    implementation(project(":forex-domain"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Tests
    testImplementation(enforcedPlatform("io.quarkus:quarkus-universe-bom:${property("quarkus.platform.version")}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}