package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaAnnotationBuilder(private val parent: JavaBuilder) : ImportBuilder by parent {

    private var type: String? = null

    private val member: MutableMap<String, String> = mutableMapOf()

    fun type(type: String) = apply {
        this.type = type.substringAfterLast(".")
        parent.importClass(type)
    }

    fun type(type: Class<*>) = apply {
        this.type = type.simpleName
        parent.importClass(type.name)
    }

    fun member(key: String, value: Any) = apply {
        when (value) {
            is String -> member[key] = "\"$value\""
            is Class<*> -> {
                member[key] = "${value.simpleName}.class"
                parent.importClass(value)
            }

            else -> member[key] = value.toString()
        }
    }

    fun build() = parent


    fun nextAnnotation(): JavaAnnotationBuilder {
        return parent.annotation()
    }

    override fun toString(): String {
        return "@$type(${member.entries.joinToString(", ") { (k, v) -> "$k = $v" }})"
    }
}
