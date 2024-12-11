package tech.medivh.generate.core.engine

import tech.medivh.generate.core.*
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.TemplateProvider
import tech.medivh.generate.core.rule.DesktopWriteRule
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Generator(
    private val contextProvider: ContextProvider,
    private val templateProvider: TemplateProvider,
    private val writeRule: WriteRule = DesktopWriteRule
) {


    fun generate(): File {
        val templates = templateProvider.getTemplates()
        val contexts = contextProvider.computeContext()
        val zip = File("generate","generate-${System.currentTimeMillis()}.zip")
        zip.parentFile.mkdirs()
        FileOutputStream(zip).use { fos ->
            ZipOutputStream(fos).use { zos ->
                templates.forEach {
                    contexts.forEach { context ->
                        val file = merge(it, context).write()
                        zos.putNextEntry(ZipEntry(file.name))
                        file.inputStream().use { input ->
                            input.copyTo(zos)
                        }
                        zos.closeEntry()
                    }
                }
            }
        }
        return zip
    }

    private fun merge(template: Template, context: GeneratorContext): FileWriter {
        return SimpleFileWriter(template, context, writeRule)
    }

}
