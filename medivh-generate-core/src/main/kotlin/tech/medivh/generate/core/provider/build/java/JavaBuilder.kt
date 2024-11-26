package tech.medivh.generate.core.provider.build.java

import java.lang.reflect.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaBuilder : ImportBuilder {

    private val imports = linkedSetOf<String>()

    private var modifier: Int = Modifier.PUBLIC

    private lateinit var className: String

    private val fieldBuilders = linkedSetOf<JavaFieldBuilder>()

    private val methodBuilders = linkedSetOf<JavaMethodBuilder>()

    /**
     * @param import class full name
     */
    override fun importClass(import: String): JavaBuilder {
        imports.add(import)
        return this
    }

    override fun importClass(clazz: Class<*>): JavaBuilder {
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

    fun finalClass(): JavaBuilder {
        this.modifier = this.modifier or Modifier.FINAL
        return this
    }

    fun field(): JavaFieldBuilder {
        return JavaFieldBuilder(this).apply {
            fieldBuilders.add(this)
        }
    }


}
