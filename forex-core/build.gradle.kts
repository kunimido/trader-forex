plugins {
    kotlin("jvm")
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Project
    implementation(project(":forex-domain"))

    // Tests
    testImplementation("org.junit.jupiter:junit-jupiter")
}