plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.keepitlock.thousandsofcoursesapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ru.keepitlock.thousandsofcoursesapp"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:core-di"))
    implementation(project(":core:core-remote"))
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-data"))
    implementation(project(":feature:feature-auth"))
    implementation(project(":feature:feature-courses"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Lifecycle
    implementation(libs.bundles.lifecycle)
    // Navigation
    implementation(libs.bundles.navigation)
    // Coroutines
    implementation(libs.bundles.coroutines)
    // Network
    implementation(libs.bundles.retrofit)
    implementation(libs.gson)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}