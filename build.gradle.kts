buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.STDLIB)
        classpath(Dependencies.Kotlin.STDLIB)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

plugins {
    id(Plugins.DETEKT) version Versions.CodeAnalyzer.DETEKT
    id(Plugins.KTLINT) version Versions.CodeAnalyzer.KTLINT
}

subprojects {
    apply(plugin = Plugins.KTLINT)
}

dependencies {
    detektPlugins(Dependencies.CodeAnalyzer.DETEKT)
}

detekt {
    toolVersion = "1.18.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    autoCorrect = true

    input = files("app/src/main/java", "app/src/main/kotlin")

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}

tasks.detekt.jvmTarget = Versions.App.JVM_TARGET

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}