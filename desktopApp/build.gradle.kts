import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.desktop.currentOs)
            }
            configurations.all {
                // some dependencies contains it, this causes an exception to initialize the Main dispatcher in desktop for image loader
                exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-android")
                exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
                exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-test")
            }
            val jvmTest by getting
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinMultiplatformComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
        dependencies {
//            commonMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
//            commonTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
//            commonTestImplementation("dev.icerock.moko:mvvm-test:0.16.1") // test utilities
        }
    }
}
