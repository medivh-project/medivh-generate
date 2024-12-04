package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaAnnotationBuilder
import tech.medivh.generate.core.provider.build.java.JavaMethodParamBuilder

/**
 * A basic implementation for building Java method parameters.
 * @author gxz gongxuanzhangmelt@gmail.com
 */
class BasicJavaMethodParamBuilder(private val parent: BuilderComponent) :
    JavaMethodParamBuilder<BasicJavaAnnotationBuilder>, ImportBuilder by parent {

    private lateinit var type: String
    private lateinit var name: String
    private var isFinal: Boolean = false
    private var isVarargs: Boolean = false
    private var annotationBuilders = ArrayList<JavaAnnotationBuilder>()


    override fun type(type: String): BasicJavaMethodParamBuilder = apply {
        this.type = type
    }

    override fun name(name: String): BasicJavaMethodParamBuilder = apply {
        this.name = name
    }

    override fun finalParam(): BasicJavaMethodParamBuilder = apply {
        isFinal = true
    }

    override fun varargs(): BasicJavaMethodParamBuilder = apply {
        isVarargs = true
    }

    override fun annotation(builder: BasicJavaAnnotationBuilder.() -> Unit): BasicJavaMethodParamBuilder = apply {
        val annotationBuilder = BasicJavaAnnotationBuilder(parent())
        annotationBuilder.apply(builder)
        annotationBuilder.checkMySelf()
        annotationBuilders.add(annotationBuilder)
    }

    override fun checkMySelf() {
        require(type.isNotBlank()) { "Parameter type must not be null or blank" }
        require(name.isNotBlank()) { "Parameter name must not be null or blank" }
    }

    override fun parent(): BuilderComponent {
        return parent
    }

    override fun text(): String {
        checkMySelf()
        val annotationsText = annotationBuilders.joinToString(" ") { it.text() }
        val finalModifier = if (isFinal) "final " else ""
        val varargsSuffix = if (isVarargs) "..." else ""
        return "$annotationsText $finalModifier$type$varargsSuffix $name"
    }

    override fun importClass(import: String): ImportBuilder {
        TODO("Not yet implemented")
    }

    override fun importClass(clazz: Class<*>): ImportBuilder {
        TODO("Not yet implemented")
    }
}
