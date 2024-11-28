package tech.medivh.generate.core.engine

import tech.medivh.generate.core.FileWriter
import tech.medivh.generate.core.SimpleFileWriter
import tech.medivh.generate.core.Template
import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.build.java.JavaBuilderGeneratorContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object MedivhGenerator {

    var writeRule: WriteRule = TempWriteRule

    fun generate() {
        val modules = ModuleLoader.loadModules()
        modules.forEach { module ->
            val templates = module.templateProvider().getTemplates()
            val contexts = module.contextProvider().computeContext()
            templates.zip(contexts, this::merge).forEach { fileWriter ->
                fileWriter.write()
            }
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

