package tech.medivh.generate.core

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GeneratorContext
import java.io.File
import java.io.StringWriter
import java.nio.file.Path
import java.util.*
import kotlin.coroutines.jvm.internal.CompletedContinuation.context


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object MedivhGenerator {

    fun generateCode(context: GeneratorContext) {
        context.templateProvider.getTemplates().forEach { template ->
            if (context.writeRule.allow(template)) {
                template.write()
            } else {
                //  todo event
            }
        }
        val velocityContext = toVelTableContext(table)
        VmTemplate.values().forEach { template ->
            if (!CompletedContinuation.context.templateFilters.all { it.allow(table, template) }) {
                return@forEach
            }

        }
    }


    fun generatorCode(tableName: String) {
        computeTargetTableDesc(tableName)
            .filter { table -> context.tableFilters.all { it.allow(table) } }
            .forEach { table -> generatorCode(table) }
        context.reporter.printReport()
    }

    private fun computeTargetTableDesc(tableName: String): List<SelectTableDesc> {
        sqlSessionFactory.mapperDo<TableMapper> { tableMapper ->
            return if (tableName == ALL_TABLES) {
                tableMapper.queryTableInfos()
            } else {
                tableMapper.queryTable(tableName)
            }
        }
        return emptyList()
    }

    private fun generatorCode(selectTableDesc: SelectTableDesc) {
        val table = selectTableDesc.fillToTable()
        val velocityContext = toVelTableContext(table)
        VmTemplate.values().forEach { template ->
            if (!context.templateFilters.all { it.allow(table, template) }) {
                return@forEach
            }
            StringWriter().use { write ->
                val tpl = Velocity.getTemplate("$TEMPLATE_DIR/${template.templateName}", "UTF-8")
                tpl.merge(velocityContext, write)
                val targetFile = getOutputTemplateFilePath(template, velocityContext).toFile()
                if (!targetFile.exists()) {
                    targetFile.writeText(write.toString())
                    return@use
                }
                if (cover) {
                    context.reporter.reportCover(targetFile)
                    val copyToFile = context.reporter.bakDir.resolve(template.subPackage.replace(".", File.separator))
                        .resolve(targetFile.name)
                    targetFile.copyTo(copyToFile, true)
                    targetFile.writeText(write.toString())
                } else {
                    context.reporter.reportIgnore(targetFile)
                }
            }
        }
    }

    private fun toVelTableContext(table: Table): VelocityContext {
        val prop = Properties()
        prop["file.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
        Velocity.init(prop)
        prop.putAll(generatorConfig.toMap())
        prop.putAll(generatorConfig.extension)
        prop.putAll(JSONObject.from(table))
        prop["pathName"] = camelToSnake(table.classname)
        return VelocityContext(prop)
    }


    private fun getOutputTemplateFilePath(template: VmTemplate, context: VelocityContext): Path {
        val fileName = template.fileNameAction(context)
        val srcPath = project.projectDir.toPath().resolve("src/main/java")
        val packagePath = srcPath.resolve(context["mainPath"].toString().replace(".", File.separator))
        val subpackage = template.subPackage.replace(".", File.separator)
        val parentPath = packagePath.resolve(subpackage)
        val parentDir = parentPath.toFile()
        parentDir.exists() || parentDir.mkdirs()
        return parentPath.resolve(fileName)
    }
}

