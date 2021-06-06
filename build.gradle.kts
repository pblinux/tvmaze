buildscript {
    val accompanistVersion by extra("0.11.1")
    val composeVersion by extra("1.0.0-beta08")
    val hiltVersion by extra("2.35")
    val kotlinVersion by extra("1.5.10")
    val pagingVersion by extra("3.0.0")
    val roomVersion by extra("2.3.0")

    repositories {
        google()
        mavenCentral()
        exclusiveContent {
            forRepository {
                maven("https://oss.sonatype.org/content/repositories/snapshots")
            }
            filter { includeModule("com.google.dagger", "hilt-android-gradle-plugin") }
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:HEAD-SNAPSHOT")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}