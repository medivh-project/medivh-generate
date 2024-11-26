package tech.medivh.generate.core.engine

import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.provider.TemplateProvider


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface GenerateModule {

    fun moduleName(): String

    fun templateProvider(): TemplateProvider

    fun contextProvider(): ContextProvider

}
