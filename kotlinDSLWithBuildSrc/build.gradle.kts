
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}


dependencies {


    // This should be in-sync with the Gradle version exposed by `Versions.kt`
    implementation("com.android.tools.build:gradle:4.2.1")

    // This should be in-sync with the Kotlin version exposed by `Versions.kt`
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")

    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation(kotlin("script-runtime"))

    implementation(project(":buildSrc"))
//    implementation(Dependencies.AndroidX.APPCOMPAT)
//    implementation(Dependencies.Google.MATERIAL)
//    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
//    implementation(Dependencies.AndroidX.LEGACY)
//
//    // Navigation
//    val version_navigation = "2.3.5"
//    implementation(Dependencies.AndroidX.NAVIGATION_FRAGMENT_KTX)
//    implementation(Dependencies.AndroidX.NAVIGATION_UI_KTX)
//
//    // ViewModel
//    val version_lifecycle = "2.4.0-beta01"
//    implementation(Dependencies.AndroidX.LIFECYCLE_LIVEDATA_KTX)
//    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
//    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_KTX)
//
//    // Glide
//    val version_glide = "4.8.0"
//    implementation(Dependencies.AndroidX.GLIDE)
//
//    // Moshi
//    val version_moshi = "1.9.3"
//    implementation(Dependencies.AndroidX.MOSHI)
//
//    // Retrofit with Moshi Converter
//    val version_retrofit = "2.9.0"
//    implementation(Dependencies.AndroidX.RETROFIT)
//
//    // Pagination
//    val pagingVersion = "3.0.1"
//    implementation(Dependencies.AndroidX.PAGINATION)
}