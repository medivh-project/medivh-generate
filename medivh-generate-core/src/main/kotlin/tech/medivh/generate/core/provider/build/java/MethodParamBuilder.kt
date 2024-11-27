package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MethodParamBuilder(private val methodBuilder: JavaMethodBuilder) : ImportBuilder by methodBuilder {

    private var name: String? = null
    private var type: String? = null

    /**
     * Sets the name of the parameter.
     * @param name the name of the parameter
     * @return the current builder instance
     */
    fun name(name: String): MethodParamBuilder = apply {
        this.name = name
    }

    /**
     * Sets the type of the parameter using its fully qualified class name as a String.
     * @param type fully qualified name of the class
     * @return the current builder instance
     */
    fun type(type: String): MethodParamBuilder = apply {
        this.type = type.substringAfterLast(".")
        importClass(type)
    }

    /**
     * Sets the type of the parameter using a Class reference.
     * @param type the Class object representing the parameter type
     * @return the current builder instance
     */
    fun type(type: Class<*>): MethodParamBuilder = apply {
        this.type = type.simpleName
        importClass(type.name)
    }

    /**
     * Completes the parameter definition and returns the parent method builder.
     * @return the parent JavaMethodBuilder instance
     */
    fun build(): JavaMethodBuilder {
        requireNotNull(name) { "Parameter name must be initialized" }
        requireNotNull(type) { "Parameter type must be initialized" }
        return methodBuilder
    }

    /**
     * Prepares for defining another parameter.
     * @return a new MethodParamBuilder instance
     */
    fun nextParam(): MethodParamBuilder {
        return build().parameters()
    }

    override fun toString(): String {
        return "$type $name"
    }
}
