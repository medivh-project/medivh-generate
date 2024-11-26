package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaBuilder {

    private val imports = linkedSetOf<String>()

    private lateinit var className: String

    private val fieldBuilders = linkedSetOf<JavaFieldBuilder>()

    private val methodBuilders = linkedSetOf<JavaMethodBuilder>()

    /**
     * @param import class full name
     */
    fun import(import: String): JavaBuilder {
        imports.add(import)
        return this
    }

    fun import(clazz: Class<*>): JavaBuilder {
        imports.add(clazz.name)
        return this
    }

    fun className(className: String): JavaBuilder {
        this.className = className
        return this
    }

    fun method(): JavaMethodBuilder {
        return JavaMethodBuilder(this).apply {
            methodBuilders.add(this)
        }
    }


}
