plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.ANDROIDX_NAVIGATION_SAFEARGS)
}

android {
    compileSdk(Versions.App.COMPILE_SDK)

    defaultConfig {
        applicationId(Versions.App.APPLICATION_ID)
        minSdk(Versions.App.MIN_SDK)
        targetSdk(Versions.App.TARGET_SDK)
        versionCode(Versions.App.VERSION_CODE)
        versionName(Versions.App.VERSION_NAME)

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        vectorDrawables.useSupportLibrary = true

    }

    buildTypes {
        getByName("release") {
            minifyEnabled = false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA
                targetCompatibility = Versions.JAVA
    }
    kotlinOptions {
        jvmTarget = Versions.App.JVM_TARGET
    }
    buildFeatures {
        viewBinding = true
    }
    androidExtensions {
        experimental = true
    }
}

dependencies {

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.LEGACY)

    // Navigation
    val version_navigation = "2.3.5"
    implementation(Dependencies.AndroidX.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.NAVIGATION_UI_KTX)

    // ViewModel
    val version_lifecycle = "2.4.0-beta01"
    implementation(Dependencies.AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_KTX)

    // Glide
    val version_glide = "4.8.0"
    implementation(Dependencies.AndroidX.GLIDE)

    // Moshi
    val version_moshi = "1.9.3"
    implementation(Dependencies.AndroidX.MOSHI)

    // Retrofit with Moshi Converter
    val version_retrofit = "2.9.0"
    implementation(Dependencies.AndroidX.RETROFIT)

    // Pagination
    val pagingVersion = "3.0.1"
    implementation(Dependencies.AndroidX.PAGINATION)

}