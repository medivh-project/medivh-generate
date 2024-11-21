package tech.medivh.generate.core.provider

import tech.medivh.generate.core.Template


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface TemplateProvider {

    fun getTemplates(): List<Template>
}
