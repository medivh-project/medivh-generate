package tech.medivh.generate.plugin.mybatisplus

import tech.medivh.generate.core.Template
import tech.medivh.generate.core.URLTemplate
import tech.medivh.generate.core.provider.TemplateProvider
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MyBatisPlusTemplateProvider : TemplateProvider {


    override fun getTemplates(): List<Template> {
        return this.javaClass.classLoader.getResources("medivh/templates/mybatis-plus/")
            .asSequence()
            .map { File(it.file) }
            .flatMap { dir -> dir.walkTopDown().filter { it.extension == "vm" }.map { it.toURI().toURL() } }
            .map { URLTemplate(it) }
            .toList()
    }
}
