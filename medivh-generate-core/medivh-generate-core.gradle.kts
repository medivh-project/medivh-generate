val velocityVersion: String by medivhVersion
val ktormVersion: String by medivhVersion
val mockitoKotlinVersion: String by medivhVersion
val fastjson2Version: String by medivhVersion
val mysqlConnectorJVersion: String by medivhVersion
val googleFormatVersion: String by medivhVersion
dependencies {
    api("org.apache.velocity:velocity-engine-core:${velocityVersion}")
    api("org.ktorm:ktorm-core:${ktormVersion}")
    api("com.alibaba.fastjson2:fastjson2:${fastjson2Version}")
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("com.google.googlejavaformat:google-java-format:$googleFormatVersion")
    api("com.mysql:mysql-connector-j:$mysqlConnectorJVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${mockitoKotlinVersion}")
    testImplementation(kotlin("test"))
}

