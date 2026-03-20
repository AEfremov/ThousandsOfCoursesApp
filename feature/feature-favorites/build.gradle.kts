plugins {
    alias(libs.plugins.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.keepitlock.featurefavorites"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-di"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)
    // Lifecycle
    implementation(libs.bundles.lifecycle)
    // Navigation
    implementation(libs.bundles.navigation)
    // Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    // Coroutines
    implementation(libs.bundles.coroutines)
    // Network
    implementation(libs.bundles.retrofit)
    // AdapterDelegates
    implementation(libs.bundles.adapterdelegates)
}