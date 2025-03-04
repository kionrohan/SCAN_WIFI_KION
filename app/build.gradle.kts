plugins {
    alias(libs.plugins.android.application)
    id ("org.sonarqube") version "6.0.1.5171"
}

sonar {
    properties {
        property("sonar.projectKey", "kiongroup_diagmngt-driver_authentication_mobile_app_AZTZvebKDOvm1MMakWhR")
    }
}


android {
    namespace = "com.example.scan_wifi_kion"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.scan_wifi_kion"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.room:room-runtime:2.6.0") // Room runtime
    annotationProcessor("androidx.room:room-compiler:2.6.0") // Room compiler for Java (annotation processor)

    // Optional: If using Room with RxJava
    implementation("androidx.room:room-rxjava2:2.6.0")

    // Optional: If using Room with Paging
    implementation("androidx.room:room-paging:2.6.0")

    // Room testing dependency
    testImplementation("androidx.room:room-testing:2.6.0")
}