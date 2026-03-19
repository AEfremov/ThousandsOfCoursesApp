plugins {
    alias(libs.plugins.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.keepitlock.coreremote"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":core:core-di"))
    implementation(libs.androidx.core.ktx)
    // Network
    api(libs.bundles.retrofit)
    api(libs.gson)
    // Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    // Coroutines
    implementation(libs.bundles.coroutines)

}