val version_detekt = "1.18.1"

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    val version_navigation = "2.3.5"
    val version_build_gradle = "7.0.2"
    val version_gradle_plugin = "1.5.31"
    dependencies {
        classpath("com.android.tools.build:gradle:$version_build_gradle")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$version_gradle_plugin")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$version_navigation")
    }
}

plugins {
    val version_detekt = "1.18.1"
    val version_ktlint = "9.2.1"
    id("io.gitlab.arturbosch.detekt") version "$version_detekt"
    id("org.jlleitschuh.gradle.ktlint") version "$version_ktlint"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(false)
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$version_detekt")
}

detekt {
    toolVersion = version_detekt
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    autoCorrect = true

    source = files("app/src/main/java", "app/src/main/kotlin")

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = "1.8"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
