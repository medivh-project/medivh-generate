package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.java.JavaClassBuilderTemplate.Companion.BUILDER_CONTEXT_KEY
import tech.medivh.generate.core.provider.build.setDefault
import tech.medivh.generate.core.provider.build.setPrivate
import tech.medivh.generate.core.provider.build.setProtected
import tech.medivh.generate.core.provider.build.setPublic
import java.lang.reflect.Modifier


/**
 * Builds Java class structures through a fluent interface.
 *
 * This builder is designed to facilitate the creation of class structures with a focus on simplicity and readability.
 * It does not guarantee that the generated class definitions will compile without errors in all environments or contexts.
 * Users are responsible for verifying the correctness, completeness, and adherence to specific project requirements,
 * including import statements, method implementations, and adherence to Java language specifications.
 *
 * @author gongxuanzhangmelt@gmail.com
 */
class JavaClassBuilder : JavaBuilderComponent {

    var packageName: String = ""

    val imports = linkedSetOf<String>()

    private var modifier: Int = Modifier.PUBLIC

    lateinit var className: String

    private val fieldBuilders = linkedSetOf<JavaFieldBuilder>()

    private val methodBuilders = linkedSetOf<JavaMethodBuilder>()

    private val annotationBuilders = linkedSetOf<JavaAnnotationBuilder>()

    private var commentBuilder = JavaCommentBuilder(this)

    override fun checkMySelf() {
        TODO("Not yet implemented")
    }

    override fun parent(): JavaBuilderComponent {
        return this
    }

    /**
     * Imports a class with the specified fully qualified name
     * @param import the fully qualified class name
     * @return the current builder instance
     */
    override fun importClass(import: String) = apply {
        //  todo illegal import
        imports.add(import)
    }

    /**
     * Imports the specified class
     * @param clazz the class to import
     * @return the current builder instance
     */
    override fun importClass(clazz: Class<*>) = apply {
        imports.add(clazz.name)
    }

    /**
     * Sets the class name. If the input contains package information (e.g. "com.example.MyClass"),
     * it will automatically set both package name and class name
     * @param fullClassName the class name, optionally including package name
     * @return the current builder instance
     */
    fun className(fullClassName: String) = apply {
        if ('.' in fullClassName) {
            packageName = fullClassName.substringBeforeLast('.')
            className = fullClassName.substringAfterLast('.')
        } else {
            className = fullClassName
        }
    }

    /**
     * Adds a method to the class
     */
    fun method(action: JavaMethodBuilder.() -> Unit = {}) = apply {
        JavaMethodBuilder(this).also {
            action(it)
            it.checkMySelf()
            methodBuilders.add(it)
        }
    }

    /**
     * Adds a field to the class
     */
    fun field(action: JavaFieldBuilder.() -> Unit = {}) = apply {
        JavaFieldBuilder(this).also {
            action(it)
            it.checkMySelf()
            fieldBuilders.add(it)
        }
    }

    /**
     * Adds an annotation to the class
     */
    fun annotation(action: JavaAnnotationBuilder.() -> Unit = {}) = apply {
        JavaAnnotationBuilder(this)
            .also { annotationBuilders.add(it) }
            .also(action).checkMySelf()
    }

    /**
     * Adds a comment to the class
     */
    fun comment(action: JavaCommentBuilder.() -> Unit = {}) = apply {
        action(commentBuilder)
        commentBuilder.checkMySelf()
    }


    /**
     * Sets the class access modifier to public
     * @return the current builder instance
     */
    fun publicClass() = apply {
        this.modifier = this.modifier.setPublic()
    }

    fun defaultClass() = apply {
        this.modifier = this.modifier.setDefault()
    }

    /**
     * Sets the class access modifier to private
     * @return the current builder instance
     */
    fun privateClass() = apply {
        this.modifier = this.modifier.setPrivate()
    }

    /**
     * Sets the class access modifier to protected
     * @return the current builder instance
     */
    fun protectedClass() = apply {
        this.modifier = this.modifier.setProtected()
    }

    /**
     * Makes the class final
     * @return the current builder instance
     */
    fun finalClass() = apply {
        this.modifier = this.modifier or Modifier.FINAL
    }

    /**
     * Makes the class abstract
     * @return the current builder instance
     */
    fun abstractClass() = apply {
        this.modifier = this.modifier or Modifier.ABSTRACT
    }

    /**
     * Makes the class static
     * @return the current builder instance
     */
    fun staticClass() = apply {
        this.modifier = this.modifier or Modifier.STATIC
    }

    fun build(): JavaBuilderGeneratorContext {
        val context = JavaBuilderGeneratorContext(this)
        context.put(BUILDER_CONTEXT_KEY, toString())
        return context
    }


    override fun toString(): String {
        return with(StringBuilder()) {
            appendLine("package $packageName;")
            //  todo import illegal
//            imports.forEach {
//                appendLine("import $it;")
//            }
            appendLine(commentBuilder.toString())
            appendLine("${Modifier.toString(modifier)} class $className {")
            fieldBuilders.forEach {
                appendLine(it.toString())
            }
            methodBuilders.forEach {
                appendLine(it.toString())
            }
            appendLine("}")
            this.toString()
        }
    }

}
