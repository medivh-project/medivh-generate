plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}


val velocityVersion: String by medivhVersion
val ktormVersion: String by medivhVersion
val mockitoKotlinVersion: String by medivhVersion
dependencies {
    implementation("org.apache.velocity:velocity-engine-core:${velocityVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("org.mockito.kotlin:mockito-kotlin:${mockitoKotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.mysql:mysql-connector-j:9.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
