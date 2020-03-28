plugins {
    kotlin("jvm")
    jacoco
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))

    // Tests
    testImplementation("org.junit.jupiter:junit-jupiter")
}