package tech.medivh.generate.core.provider.build

import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.Template
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.TemplateProvider
import tech.medivh.generate.core.provider.build.java.JavaClassBuilder
import tech.medivh.generate.core.provider.build.java.JavaClassBuilderTemplate


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class BuilderProvider(private val javaClassBuilderList: List<JavaClassBuilder<*, *, *, *>>) : ContextProvider,
    TemplateProvider {

    override fun computeContext(): List<GeneratorContext> {
        return javaClassBuilderList.map { it.build() }.toList()
    }

    override fun getTemplates(): List<Template> {
        return listOf(JavaClassBuilderTemplate())
    }


}
