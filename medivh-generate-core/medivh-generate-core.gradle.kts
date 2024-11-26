val velocityVersion: String by medivhVersion
val ktormVersion: String by medivhVersion
val mockitoKotlinVersion: String by medivhVersion
val fastjson2Version: String by medivhVersion
dependencies {
    implementation("org.apache.velocity:velocity-engine-core:${velocityVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("org.mockito.kotlin:mockito-kotlin:${mockitoKotlinVersion}")
    implementation("com.alibaba.fastjson2:fastjson2:${fastjson2Version}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.mysql:mysql-connector-j:9.1.0")
    testImplementation(kotlin("test"))
}

