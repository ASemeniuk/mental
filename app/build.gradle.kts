plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "org.alexsem.mental"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.alexsem.mental"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("com.android.support:appcompat-v7:24.2.1")
    implementation("com.android.support:design:24.2.1")
    implementation("com.android.support.constraint:constraint-layout:1.0.2")
    implementation("com.github.ganfra:material-spinner:1.1.1")
    implementation("com.android.support:support-v4:24.2.1")
    testImplementation("junit:junit:4.12")
}