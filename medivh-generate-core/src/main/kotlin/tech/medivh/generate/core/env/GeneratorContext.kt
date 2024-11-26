package tech.medivh.generate.core.env

import org.apache.velocity.context.Context
import tech.medivh.generate.core.provider.TemplateProvider


/**
 *
 * [GeneratorContext] context will be Cartesian product with the [TemplateProvider] to produce Writer
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface GeneratorContext : Context {

    fun putAll(properties: Map<String, Any>)

}
