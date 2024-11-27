package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
abstract class JavaAnnotationBuilder<P : ImportBuilder>(val parent: P) : ImportBuilder by parent {

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

    override fun toString(): String {
        if(member.isEmpty()){
            return "@$type"
        }
        return "@$type(${member.entries.joinToString(", ") { (k, v) -> "$k = $v" }})"
    }
}

class FieldAnnotationBuilder(parent: JavaFieldBuilder) : JavaAnnotationBuilder<JavaFieldBuilder>(parent) {
    fun nextAnnotation() = parent.annotation()
}

class MethodAnnotationBuilder(parent: JavaMethodBuilder) : JavaAnnotationBuilder<JavaMethodBuilder>(parent) {
    fun nextAnnotation() = parent.annotation()
}

class ClassAnnotationBuilder(parent: JavaBuilder) : JavaAnnotationBuilder<JavaBuilder>(parent) {
    fun nextAnnotation() = parent.annotation()
}
