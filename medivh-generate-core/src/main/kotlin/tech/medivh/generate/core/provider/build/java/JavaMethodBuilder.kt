package tech.medivh.generate.core.provider.build.java

import javax.lang.model.element.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaMethodBuilder(private val javaBuilder: JavaBuilder) {
    private var modifier: Modifier = Modifier.PUBLIC
    private var returnType = "void"
    private lateinit var name: String
    private val paramsBuilders = linkedSetOf<MethodParamBuilder>()
    private var body: String = ""

    fun modifier(modifier: Modifier): JavaMethodBuilder {
        this.modifier = modifier
        return this
    }

    fun modifier(modifier: String): JavaMethodBuilder {
        this.modifier = Modifier.valueOf(modifier)
        return this
    }

    fun returnType(returnType: String): JavaMethodBuilder {
        this.returnType = returnType
        return this
    }


    fun name(name: String): JavaMethodBuilder {
        this.name = name
        return this
    }

    fun parameters(): MethodParamBuilder {
        return MethodParamBuilder(this).apply {
            paramsBuilders.add(this)
        }
    }

    fun body(body: String): JavaMethodBuilder {
        this.body = body
        return this
    }

    fun nextMethod(): JavaMethodBuilder {
        return javaBuilder.method()
    }

}

