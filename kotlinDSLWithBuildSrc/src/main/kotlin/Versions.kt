import org.gradle.api.JavaVersion

object Versions {
    object App {
        const val APPLICATION_ID = "com.example.rsschool2021_android_task_5"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
        const val MIN_SDK = 21
        const val TARGET_SDK = 31
        const val COMPILE_SDK = 31
        const val JVM_TARGET = "1.8"
    }

    object Google {
        const val MATERIAL = "1.4.0"
    }

    object AndroidX {
        const val CORE = "1.6.0"
        const val APPCOMPAT = "1.3.1"
        const val CONSTRAINT_LAYOUT = "2.1.1"
        const val LEGACY = "1.0.0"
        const val NAVIGATION = "2.3.5"
        const val LIFECYCLE = "2.4.0-beta01"
        const val GLIDE = "4.8.0"
        const val MOSHI = "1.9.3"
        const val RETROFIT = "2.9.0"
        const val PAGINATION = "3.0.1"
    }

    object CodeAnalyzer {
        const val DETEKT = "1.18.1"
        const val KTLINT = "9.2.1"
    }

    object Test {
        const val JUNIT = "4.13.2"
        const val JUNIT_INTEGRATION = "1.1.2"
        const val ESPRESSO = "3.3.0"
    }

    const val GRADLE = "7.0.2"
    const val KOTLIN = "1.5.31"

    val JAVA = JavaVersion.VERSION_1_8
}