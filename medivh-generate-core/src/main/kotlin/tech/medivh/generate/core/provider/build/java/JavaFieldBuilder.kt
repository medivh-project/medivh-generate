package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.setDefault
import tech.medivh.generate.core.provider.build.setPrivate
import tech.medivh.generate.core.provider.build.setProtected
import tech.medivh.generate.core.provider.build.setPublic
import java.lang.reflect.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaFieldBuilder(private val parent: JavaClassBuilder) : JavaBuilderComponent, ImportBuilder by parent {

    private var name: String? = null
    private var type: String? = null
    private var modifier: Int = Modifier.PRIVATE
    private val annotationBuilders = linkedSetOf<JavaAnnotationBuilder>()
    private val commentBuilder = JavaCommentBuilder(this)

    /**
     * Sets the name of the field.
     * @param name the name of the field
     * @return the current builder instance
     */
    fun name(name: String): JavaFieldBuilder = apply {
        this.name = name
    }

    /**
     * Sets the type of the field using its fully qualified class name as a String.
     * @param type fully qualified name of the class
     * @return the current builder instance
     */
    fun type(type: String): JavaFieldBuilder = apply {
        this.type = type.substringAfterLast(".")
        parent.importClass(type)
    }

    /**
     * Sets the type of the field using a Class reference.
     * @param type the Class object representing the field type
     * @return the current builder instance
     */
    fun type(type: Class<*>): JavaFieldBuilder = apply {
        this.type = type.simpleName
        parent.importClass(type.name)
    }

    /**
     * Adds a comment to the field
     */
    fun comment(action: JavaCommentBuilder.() -> Unit = {}) = apply {
        action(commentBuilder)
        commentBuilder.checkMySelf()
    }

    /**
     * Adds an annotation to the field
     */
    fun annotation(action: JavaAnnotationBuilder.() -> Unit = {}) = apply {
        JavaAnnotationBuilder(this)
            .also { annotationBuilders.add(it) }
            .also(action).checkMySelf()
    }

    /**
     * Makes the field private (default)
     */
    fun privateField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier.setPrivate()
    }

    /**
     * Makes the field public
     */
    fun publicField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier.setPublic()
    }

    /**
     * Makes the field protected
     */
    fun protectedField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier.setProtected()
    }

    /**
     * Makes the field default
     */
    fun defaultField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier.setDefault()
    }


    /**
     * Makes the field final
     */
    fun finalField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier or Modifier.FINAL
    }

    /**
     * Makes the field static
     */
    fun staticField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier or Modifier.STATIC
    }

    /**
     * Makes the field synchronized
     */
    fun synchronizedField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier or Modifier.SYNCHRONIZED
    }

    /**
     * Makes the field volatile
     */
    fun volatileField(): JavaFieldBuilder = apply {
        this.modifier = this.modifier or Modifier.VOLATILE
    }


    override fun checkMySelf() {
        require(name.isNullOrBlank().not()) { "Field name must be initialized and non-blank" }
        require(type.isNullOrBlank().not()) { "Field type must be initialized and non-blank" }
    }

    override fun parent(): JavaBuilderComponent {
        return parent
    }

    override fun toString(): String {
        return "$commentBuilder\n${annotationBuilders.joinToString("\n")}\n${Modifier.toString(modifier)} $type $name;"
    }

}

