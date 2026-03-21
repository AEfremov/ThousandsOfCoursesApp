plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "ru.keepitlock.coreui"
    compileSdk {
        version = release(36)
    }
    defaultConfig {
        minSdk = 30
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.fragment.ktx)
    // Lifecycle
    api(libs.bundles.lifecycle)

    implementation(libs.bundles.adapterdelegates)
}