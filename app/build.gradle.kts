plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = AppConfigurations.namespace
    compileSdk = AppConfigurations.compileSdk

    defaultConfig {
        applicationId = AppConfigurations.applicationId
        minSdk = AppConfigurations.minSdk
        targetSdk = AppConfigurations.targetSdk
        versionCode = AppConfigurations.versionCode
        versionName = AppConfigurations.versionName

        testInstrumentationRunner = AppConfigurations.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(AppConfigurations.defaultProguardFiles), AppConfigurations.proguardConsumerRules)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(AppDependencies.appLibraries)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    kapt(AppDependencies.kapts)
}

kapt {
    correctErrorTypes = true
}