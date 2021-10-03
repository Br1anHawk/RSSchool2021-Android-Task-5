import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {
    object Gradle {
        const val STDLIB = "com.android.tools.build:gradle:${Versions.GRADLE}"
    }

    object Kotlin {
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:${Versions.AndroidX.CORE}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APPCOMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"
        const val LEGACY = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.LEGACY}"

        const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.NAVIGATION}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.NAVIGATION}"

        const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE}"

        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.AndroidX.GLIDE}"
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.AndroidX.MOSHI}"
        const val RETROFIT = "com.squareup.retrofit2:converter-moshi:${Versions.AndroidX.RETROFIT}"
        const val PAGINATION = "androidx.paging:paging-runtime-ktx:${Versions.AndroidX.PAGINATION}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"
    }

    object CodeAnalyzer {
        const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.CodeAnalyzer.DETEKT}"
    }

    object Test {
        object Unit {
            const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        }

        object Integration {
            const val JUNIT = "androidx.test.ext:junit:${Versions.Test.JUNIT_INTEGRATION}"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
        }
    }
}