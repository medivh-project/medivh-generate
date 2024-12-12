package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaAnnotationBuilder

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class BasicJavaAnnotationBuilder(private val parent: BuilderComponent) : JavaAnnotationBuilder,
    ImportBuilder by parent {

    private var annotationName: String? = null
    private val parameters = mutableMapOf<String, String>()

    override fun name(name: String): JavaAnnotationBuilder = apply {
        this.annotationName = name
    }

    override fun value(value: String): JavaAnnotationBuilder = apply {
        check("value" !in parameters) { "Annotation value and value parameter can't exist at the same time" }
        parameters["value"] = value
    }

    override fun param(name: String, value: String): JavaAnnotationBuilder = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        parameters[name] = value
    }

    override fun stringParam(name: String, value: String): JavaAnnotationBuilder = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        parameters[name] = "\"$value\""
    }

    override fun arrayParam(name: String, values: List<String>): JavaAnnotationBuilder = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        parameters[name] = "{${values.joinToString(", ")}}"
    }

    override fun arrayParamString(name: String, values: List<String>): JavaAnnotationBuilder = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        parameters[name] = values.joinToString(prefix = "{", postfix = "}") { "\"$it\"" }
    }

    override fun classParam(name: String, className: String): JavaAnnotationBuilder = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        parameters[name] = "$className.class"
    }

    override fun nestedAnnotation(name: String, annotationAction: JavaAnnotationBuilder.() -> Unit) = apply {
        check(name !in parameters) { "Annotation parameter $name already exists" }
        BasicJavaAnnotationBuilder(this)
            .also(annotationAction)
            .let {
                checkMySelf()
                parameters[name] = toString()
            }
    }

    override fun checkMySelf() {
        require(!annotationName.isNullOrBlank()) { "Annotation name must not be null or blank" }
        parameters.forEach { (key, value) ->
            require(key.isNotBlank()) { "Parameter key must not be blank" }
            require(value.isNotBlank()) { "Parameter value for $key must not be blank" }
        }
    }

    override fun parent(): BuilderComponent {
        return parent
    }


    override fun text(): String {
        return when {
            parameters.isEmpty() -> "@$annotationName"
            parameters.size == 1 && "value" in parameters -> "@$annotationName(${parameters["value"]})"
            else -> "@$annotationName(${parameters.entries.joinToString(", ") { (key, value) -> "$key = $value" }})"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BasicJavaAnnotationBuilder

        if (annotationName != other.annotationName) return false
        if (parameters != other.parameters) return false

        return true
    }

    override fun hashCode(): Int {
        var result = annotationName?.hashCode() ?: 0
        result = 31 * result + parameters.hashCode()
        return result
    }


    override fun toString(): String {
        return text()
    }

}
