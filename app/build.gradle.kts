plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.rsschool2021_android_task_5"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Navigation
    val version_navigation = "2.3.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$version_navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$version_navigation")

    // ViewModel
    val version_lifecycle = "2.4.0-beta01"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$version_lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$version_lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$version_lifecycle")

    // Glide
    val version_glide = "4.8.0"
    implementation("com.github.bumptech.glide:glide:$version_glide")

    // Moshi
    val version_moshi = "1.9.3"
    implementation("com.squareup.moshi:moshi-kotlin:$version_moshi")

    // Retrofit with Moshi Converter
    val version_retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:converter-moshi:$version_retrofit")

    // Pagination
    val pagingVersion = "3.0.1"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
}
