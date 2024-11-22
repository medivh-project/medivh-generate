package tech.medivh.generate.core


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/

fun String.convert(targetStyle: NameStyle): String {
    return when (targetStyle) {
        NameStyle.CAMEL -> this.toCamelCase()
        NameStyle.UNDERLINE -> this.toUnderlineCase()
        NameStyle.HUMP -> this.toHumpCase()
    }
}

private fun String.toCamelCase(): String {
    return this.toLowerCase().split("_").mapIndexed { index, part ->
        if (index == 0) part else part.replaceFirstChar { it.uppercaseChar() }
    }.joinToString("")
}

private fun String.toUnderlineCase(): String {
    return this.split("(?=[A-Z])".toRegex()).joinToString("_") { it.lowercase() }
        .replace("__", "_")
}

private fun String.toHumpCase(): String {
    return this.toLowerCase().split("_").joinToString("") { it.replaceFirstChar { ch -> ch.uppercaseChar() } }
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




