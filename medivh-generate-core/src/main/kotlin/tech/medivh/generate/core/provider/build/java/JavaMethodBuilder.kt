package tech.medivh.generate.core.provider.build.java

import javax.lang.model.element.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaMethodBuilder(private val javaBuilder: JavaBuilder) :ImportBuilder by javaBuilder{
    private var modifier: Modifier = Modifier.PUBLIC
    private var returnType = "void"
    private lateinit var name: String
    private val paramsBuilders = linkedSetOf<MethodParamBuilder>()
    private var body: String = ""

    fun modifier(modifier: Modifier): JavaMethodBuilder {
        this.modifier = modifier
        return this
    }

    fun modifier(modifier: String) = apply {
        this.modifier = Modifier.valueOf(modifier)
    }

    fun returnType(returnType: String) = apply {
        this.returnType = returnType
    }


    fun name(name: String) = apply {
        this.name = name
    }

    fun parameters(): MethodParamBuilder {
        return MethodParamBuilder(this).apply {
            paramsBuilders.add(this)
        }
    }


    fun body(body: String) = apply {
        this.body = body
    }

    fun nextMethod(): JavaMethodBuilder {
        return javaBuilder.method()
    }

}

