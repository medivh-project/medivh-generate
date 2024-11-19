rootProject.name = "medivh-generate"

pluginManagement {
    val buildKotlinVersion: String by settings
    val publishVersion: String by settings

    plugins {
        kotlin("jvm") version buildKotlinVersion apply false
        id("com.gradle.plugin-publish") version publishVersion apply false
    }

}

include(":medivh-generate-core")
include(":medivh-generate-gradle-plugin")

rootProject.children.forEach { it.configureBuildScriptName() }

fun ProjectDescriptor.configureBuildScriptName() {
    buildFileName = "${name}.gradle.kts"
    children.forEach { it.configureBuildScriptName() }
}

