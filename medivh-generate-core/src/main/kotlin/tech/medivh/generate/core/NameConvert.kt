package tech.medivh.generate.core


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/

fun String.convert(targetStyle: NameStyle): String {
    return when (targetStyle) {
        NameStyle.CAMEL -> this.toCamelCase()
        NameStyle.UNDERLINE -> this.toSnakeCase()
        NameStyle.HUMP -> this.toHumpCase()
    }
}


private fun String.toCamelCase(): String {
    return toHumpCase().replaceFirstChar { char -> char.lowercase() }
}

private fun String.toSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z])"), "$1_$2")
        .replace(Regex("([A-Z]+)([A-Z][a-z])"), "$1_$2")
        .lowercase()
}

private fun String.toHumpCase(): String {
    val parts = this.split('_')
    return parts.joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }
}

enum class NameStyle {
    /**
     * helloWorld
     */
    CAMEL,

    /**
     * hello_world
     */
    UNDERLINE,

    /**
     * HelloWorld
     */
    HUMP;
}




