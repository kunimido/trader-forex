import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id("io.quarkus")
    jacoco
}

dependencies {
    // Project
    implementation(project(":forex-core"))
    implementation(project(":forex-domain"))

    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${property("kotlinx.coroutines.version")}")

    // Frameworks
    implementation(enforcedPlatform("io.quarkus:quarkus-universe-bom:${property("quarkus.platform.version")}"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.quarkus:quarkus-container-image-jib")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging-kafka")

    // Tests
    testImplementation("io.mockk:mockk:${property("mockk.version")}")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:kotlin-extensions")
    testImplementation("io.smallrye.reactive:smallrye-reactive-messaging-in-memory")
}

allOpen {
    annotation("io.quarkus.test.junit.QuarkusTest")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("javax.ws.rs.Path")
}

tasks {
    processResources {
        filesMatching("application.properties") {
            filter(ReplaceTokens::class,
                    "tokens" to project.properties.filterValues { v -> v != null && v is String })
        }
    }

    test {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    }
}