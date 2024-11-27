val velocityVersion: String by medivhVersion
val ktormVersion: String by medivhVersion
val mockitoKotlinVersion: String by medivhVersion
val fastjson2Version: String by medivhVersion
val mysqlConnectorJVersion: String by medivhVersion
val googleFormatVersion: String by medivhVersion
dependencies {
    implementation("org.apache.velocity:velocity-engine-core:${velocityVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("com.alibaba.fastjson2:fastjson2:${fastjson2Version}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.google.googlejavaformat:google-java-format:$googleFormatVersion")
    implementation("com.mysql:mysql-connector-j:$mysqlConnectorJVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${mockitoKotlinVersion}")
    testImplementation(kotlin("test"))
}

