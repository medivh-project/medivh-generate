rootProject.name = "medivh-generate"

pluginManagement {
    val buildKotlinVersion: String by settings
    val publishVersion: String by settings

    plugins {
        kotlin("jvm") version buildKotlinVersion apply false
        id("com.gradle.plugin-publish") version publishVersion apply false
    }

}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include(":medivh-generate-core")
include(":medivh-generate-gradle-plugin")
include(":medivh-generate-plugin")
include(":medivh-generate-console")
include(":medivh-generate-plugin:medivh-generate-plugin-mybatis")
include(":medivh-generate-plugin:medivh-generate-plugin-mybatis-plus")
include(":medivh-generate-plugin:medivh-generate-plugin-spring")

rootProject.children.forEach { it.configureBuildScriptName() }

fun ProjectDescriptor.configureBuildScriptName() {
    buildFileName = "${name}.gradle.kts"
    children.forEach { it.configureBuildScriptName() }
}

