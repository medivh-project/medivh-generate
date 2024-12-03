val fastjson2Version: String by medivhVersion
subprojects {
    dependencies {
        api(project(":medivh-generate-core"))
    }
}
