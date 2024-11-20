package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator

import com.alibaba.fastjson2.JSONObject
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.SelectTableDesc
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.Table
import java.io.File
import java.io.StringWriter
import java.nio.file.Path
import java.util.*
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.gradle.api.Project


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class CodeGenerator(
    private val project: Project,
    private val cover: Boolean,
    private val context: GeneratorContext
) {


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



