package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaAnnotationBuilder(private val parent: JavaBuilderComponent) : ImportBuilder by parent, JavaBuilderComponent {

    private var type: String? = null

    private val attributes: MutableMap<String, String> = mutableMapOf()


    fun type(type: String) = apply {
        this.type = type.substringAfterLast(".")
        parent.importClass(type)
    }

    fun type(type: Class<*>) = apply {
        this.type = type.simpleName
        parent.importClass(type.name)
    }

    fun attr(key: String, value: Any) = apply {
        when (value) {
            is String -> this.attributes[key] = "\"$value\""
            is Class<*> -> {
                this.attributes[key] = "${value.simpleName}.class"
                parent.importClass(value)
            }

            else -> this.attributes[key] = value.toString()
        }
    }


    override fun checkMySelf() {
        requireNotNull(type) { "annotation type must not null" }
        //  todo invoke class#forName check value

    }

    override fun parent() = parent

    override fun toString(): String {
        if (attributes.isEmpty()) {
            return "@$type"
        }
        return "@$type(${attributes.entries.joinToString(", ") { (k, v) -> "$k = $v" }})"
    }
}

