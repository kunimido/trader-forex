import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply false
    id("io.spring.dependency-management")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${property("spring.boot.version")}")
        }

    }

    configurations {
        val developmentOnly by creating
        create("runtimeOnly").extendsFrom(developmentOnly)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "13"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}