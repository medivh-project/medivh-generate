package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.setDefault
import tech.medivh.generate.core.provider.build.setPrivate
import tech.medivh.generate.core.provider.build.setProtected
import tech.medivh.generate.core.provider.build.setPublic
import java.lang.reflect.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaMethodBuilder(private val javaBuilder: JavaBuilder) : ImportBuilder by javaBuilder {
    private var modifier: Int = Modifier.PUBLIC
    private var returnType = "void"
    private var name: String? = null
    private val paramsBuilders = linkedSetOf<MethodParamBuilder>()
    private val annotationBuilders = linkedSetOf<MethodAnnotationBuilder>()
    private var body: String = ""
    private val commentBuilder = MethodCommentBuilder(this)

    fun returnType(returnType: String) = apply {
        this.returnType = returnType
    }

    fun name(name: String) = apply {
        this.name = name
    }

    fun parameters(): MethodParamBuilder {
        return MethodParamBuilder(this).apply {
            paramsBuilders.add(this)
        }
    }

    /**
     * Makes the method private
     */
    fun privateMethod() = apply {
        this.modifier = this.modifier.setPrivate()
    }

    /**
     * Makes the method public (default)
     */
    fun publicMethod() = apply {
        this.modifier = this.modifier.setPublic()
    }

    /**
     * Makes the method protected
     */
    fun protectedMethod() = apply {
        this.modifier = this.modifier.setProtected()
    }

    /**
     * Makes the method default
     */
    fun defaultMethod() = apply {
        this.modifier = this.modifier.setDefault()
    }

    fun body(body: String) = apply {
        this.body = body
    }

    fun nextMethod(): JavaMethodBuilder {
        return javaBuilder.method()
    }

    fun annotation(): MethodAnnotationBuilder {
        return MethodAnnotationBuilder(this).apply {
            annotationBuilders.add(this)
        }
    }

    override fun toString(): String {
        return "$commentBuilder\n${Modifier.toString(modifier)} $returnType $name(${paramsBuilders.joinToString(", ")}) { \n$body }"
    }

}

