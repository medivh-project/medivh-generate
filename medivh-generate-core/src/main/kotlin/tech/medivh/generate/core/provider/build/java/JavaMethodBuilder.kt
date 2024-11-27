package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.setDefault
import tech.medivh.generate.core.provider.build.setPrivate
import tech.medivh.generate.core.provider.build.setProtected
import tech.medivh.generate.core.provider.build.setPublic
import java.lang.reflect.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaMethodBuilder(private val parent: JavaClassBuilder) : JavaBuilderComponent, ImportBuilder by parent {
    private var modifier: Int = Modifier.PUBLIC
    private var returnType = "void"
    private var name: String? = null
    private val paramsBuilders = linkedSetOf<MethodParamBuilder>()
    private val annotationBuilders = linkedSetOf<JavaAnnotationBuilder>()
    private var body: String = ""
    private val commentBuilder = JavaCommentBuilder(this)


    fun returnType(returnType: String) = apply {
        this.returnType = returnType
    }

    fun name(name: String) = apply {
        this.name = name
    }

    fun parameters(action: MethodParamBuilder.() -> Unit = {}): MethodParamBuilder {
        return MethodParamBuilder(this).apply {
            paramsBuilders.add(this)
            action(this)
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

    fun build() = parent

    /**
     * Adds an annotation to the method
     */
    fun annotation(action: JavaAnnotationBuilder.() -> Unit = {}) = apply {
        JavaAnnotationBuilder(this)
            .also { annotationBuilders.add(it) }
            .also(action).checkMySelf()
    }

    /**
     * Adds a comment to the method
     */
    fun comment(action: JavaCommentBuilder.() -> Unit = {}) = apply {
        action(commentBuilder)
        commentBuilder.checkMySelf()
    }

    override fun checkMySelf() {
        //   todo check
    }

    override fun parent(): JavaBuilderComponent {
        return parent
    }

    override fun toString(): String {
        return "$commentBuilder\n${Modifier.toString(modifier)} $returnType $name(${paramsBuilders.joinToString(", ")}) { \n$body }"
    }

}

