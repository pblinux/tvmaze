plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "me.pblinux.tvmaze"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["composeVersion"] as String
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
    // Android base
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("com.google.android.material:material:1.3.0")
    // Compose
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha06")
    // Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltVersion"]}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra["hiltVersion"]}")
    // Paging
    implementation("androidx.paging:paging-runtime:${rootProject.extra["pagingVersion"]}")
    implementation("androidx.paging:paging-compose:1.0.0-alpha10")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha02")
    // Room
    annotationProcessor("androidx.room:room-compiler:${rootProject.extra["roomVersion"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["roomVersion"]}")
    implementation("androidx.room:room-runtime:${rootProject.extra["roomVersion"]}")
    kapt("androidx.room:room-compiler:${rootProject.extra["roomVersion"]}")
    // Third-party
    //Accompanist
    implementation("com.google.accompanist:accompanist-coil:${rootProject.extra["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-flowlayout:${rootProject.extra["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-pager-indicators:${rootProject.extra["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-pager:${rootProject.extra["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${rootProject.extra["accompanistVersion"]}")
    // SquareUp
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    // Nested Scrolling
    implementation("com.github.Tlaster:NestedScrollView:0.5.0")
    // Rating
    implementation("com.github.a914-gowtham:compose-ratingbar:1.0.63")
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["composeVersion"]}")
}