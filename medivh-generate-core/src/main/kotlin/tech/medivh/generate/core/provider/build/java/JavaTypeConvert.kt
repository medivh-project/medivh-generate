package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object JavaTypeConvert {

    /**
     * These classes don't need import
     */
    private val defaultClasses = setOf(
        "String",
        "Integer",
        "Long",
        "Double",
        "Float",
        "Boolean",
        "Short",
        "Byte",
        "Character",
        "int",
        "char",
        "short",
        "byte",
        "long",
        "float",
        "double",
        "void"
    )


    /**
     * convert simple type to full type
     * String --> java.lang.String
     *
     */
    fun fullName(type: String): String? {
        if ("." in type) {
            return type
        }
        if (type in defaultClasses) {
            return null
        }
        throw IllegalArgumentException("Invalid import statement: $type , please provide a fully qualified class name like MyClass -> tech.medivh.generate.MyClass")
    }
}
