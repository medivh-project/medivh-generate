package tech.medivh.generate.core.engine

import tech.medivh.generate.core.FileWriter
import tech.medivh.generate.core.SimpleFileWriter
import tech.medivh.generate.core.Template
import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.TemplateProvider
import tech.medivh.generate.core.provider.build.java.JavaBuilderGeneratorContext
import tech.medivh.generate.core.source.DataSourceFacade
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Generator(private val sourceFacade: DataSourceFacade, private val templateProvider: TemplateProvider) {

    var writeRule: WriteRule = TempWriteRule

    fun generate() {
        val templates = templateProvider.getTemplates()
        val contexts = sourceFacade.computeContext()
        templates.zip(contexts, this::merge).forEach { fileWriter ->
            fileWriter.write()
        }
    }

    private fun merge(template: Template, context: GeneratorContext): FileWriter {
        return SimpleFileWriter(template, context, writeRule)
    }

}

// todo
object TempWriteRule : WriteRule {

    override fun overwrite(): Boolean {
        return true
    }

    override fun targetFile(template: Template, context: GeneratorContext): File {
        if (context is JavaBuilderGeneratorContext) {
            TODO()
//            return File("${context.builder.className}.java")
        }
        TODO()
    }

    override fun format(): Boolean {
        return true
    }

}

