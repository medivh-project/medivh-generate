import tech.medivh.generate.build.Versions

plugins {
    kotlin("jvm")
}

val versionsFile: String by properties

val rootVersionFile = file(versionsFile)

allprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "tech.medivh"
    version = "0.0.1"

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin {
        jvmToolchain(11)
    }

    extensions.create("medivhVersion", Versions::class.java, rootVersionFile)
}



