package tech.medivh.generate.core.engine

import tech.medivh.generate.core.*
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.TemplateProvider
import java.io.File
import java.nio.file.Paths


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Generator(
    private val contextProvider: ContextProvider,
    private val templateProvider: TemplateProvider,
    private val writeRule: WriteRule = DesktopWriteRule
) {


    fun generate() {
        val templates = templateProvider.getTemplates()
        val contexts = contextProvider.computeContext()
        templates.forEach {
            contexts.forEach { context ->
                merge(it, context).write()
            }
        }
    }

    private fun merge(template: Template, context: GeneratorContext): FileWriter {
        return SimpleFileWriter(template, context, writeRule)
    }

}

object DesktopWriteRule : WriteRule {

    private val desktopDir = getDesktopFolder()

    override fun overwrite(): Boolean {
        return true
    }

    override fun targetFile(template: Template, context: GeneratorContext): File {
        return desktopDir.resolve("${context["className"]}${template.templateName().substringBeforeLast(".")}")
    }


    override fun format(): Boolean {
        return true
    }

    private fun getDesktopFolder(): File {
        val userHome = System.getProperty("user.home") // 获取用户主目录
        Paths.get(userHome, "Desktop").toFile().let {
            if (!it.exists()) {
                it.mkdirs()
            }
            return it
        }
    }

}

