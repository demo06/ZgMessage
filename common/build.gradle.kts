import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "funny.buildapp"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            val rSocketVersion = "0.13.1"
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)

                //RSocket & Ktor
                implementation("io.rsocket.kotlin:rsocket-core:$rSocketVersion")
                implementation("io.rsocket.kotlin:rsocket-transport-ktor:$rSocketVersion") // TCP ktor transport
                implementation("io.rsocket.kotlin:rsocket-transport-ktor-client:$rSocketVersion")// WS ktor transport client plugin
                implementation("io.rsocket.kotlin:rsocket-transport-ktor-server:$rSocketVersion")// WS ktor transport server plugin
                implementation("io.ktor:ktor-client-cio:1.6.2")
                implementation("io.ktor:ktor-server-cio:1.6.2")

            }
        }
        val commonTest by getting {
            dependencies {
//                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.3.0")
                api("androidx.core:core-ktx:1.3.1")
                api("androidx.compose.ui:ui-tooling:1.0.4") // for preview in android
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}