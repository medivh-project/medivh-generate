plugins {
    kotlin("jvm")
}

group = "tech.medivh"
version = "0.2.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}