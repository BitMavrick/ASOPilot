plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = Config.APP_ID + ".core.model"
    compileSdk {
        version = release(
            version = Config.COMPILE_SDK_VERSION
        )
    }

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}