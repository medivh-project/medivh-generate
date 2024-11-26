package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaFieldBuilder(private val javaBuilder: JavaBuilder) : ImportBuilder by javaBuilder {

    private var name: String? = null
    private var type: String? = null

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
        javaBuilder.importClass(type)
    }

    /**
     * Sets the type of the field using a Class reference.
     * @param type the Class object representing the field type
     * @return the current builder instance
     */
    fun type(type: Class<*>): JavaFieldBuilder = apply {
        this.type = type.simpleName
        javaBuilder.importClass(type.name)
    }

    /**
     * Completes the field definition and returns the parent JavaBuilder instance.
     * @return the parent JavaBuilder instance
     */
    fun build(): JavaBuilder {
        require(name.isNullOrBlank().not()) { "Field name must be initialized and non-blank" }
        require(type.isNullOrBlank().not()) { "Field type must be initialized and non-blank" }
        return javaBuilder
    }

    /**
     * Prepares for defining another field.
     * @return a new JavaFieldBuilder instance
     */
    fun nextField(): JavaFieldBuilder {
        return javaBuilder.field()
    }
}

