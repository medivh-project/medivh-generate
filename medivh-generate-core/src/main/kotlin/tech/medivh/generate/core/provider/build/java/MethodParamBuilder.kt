package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MethodParamBuilder(private val parent: JavaMethodBuilder) : JavaBuilderComponent, ImportBuilder by parent {

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


    override fun checkMySelf() {
        requireNotNull(name) { "Parameter name must be initialized" }
        requireNotNull(type) { "Parameter type must be initialized" }
    }

    override fun parent(): JavaMethodBuilder {
        return parent
    }

    override fun toString(): String {
        return "$type $name"
    }
}
