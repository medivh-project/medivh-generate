package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaMethodParamBuilder


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class BasicJavaMethodParamBuilder : JavaMethodParamBuilder<BasicJavaAnnotationBuilder> {

    override fun type(type: String): BasicJavaMethodParamBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun name(name: String): BasicJavaMethodParamBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun finalParam(): BasicJavaMethodParamBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun varargs(): BasicJavaMethodParamBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun annotation(builder: BasicJavaAnnotationBuilder.() -> Unit): BasicJavaMethodParamBuilder = apply {

    }

    override fun checkMySelf() {
        TODO("Not yet implemented")
    }

    override fun parent(): BuilderComponent {
        TODO("Not yet implemented")
    }

    override fun text(): String {
        TODO("Not yet implemented")
    }

    override fun importClass(import: String): ImportBuilder {
        TODO("Not yet implemented")
    }

    override fun importClass(clazz: Class<*>): ImportBuilder {
        TODO("Not yet implemented")
    }
}
