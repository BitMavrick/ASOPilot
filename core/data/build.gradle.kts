plugins {
    alias(libs.plugins.android.library)
    id("com.chaquo.python")
}

android {
    namespace = Config.APP_ID + ".core.data"
    compileSdk {
        version = release(
            version = Config.COMPILE_SDK_VERSION
        )
    }

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters += listOf("arm64-v8a", "x86_64")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

chaquopy {
    defaultConfig {
        version = "3.13"

        pip {
            install("google-play-scraper")
        }
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}