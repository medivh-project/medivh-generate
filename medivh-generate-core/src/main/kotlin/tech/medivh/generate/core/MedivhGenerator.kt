package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GeneratorContext
import java.util.*


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object MedivhGenerator {


    init {
        val prop = Properties()
        prop["file.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
        Velocity.init(prop)
    }

    fun generateCode(context: GeneratorContext) {
        context.templateProvider.getTemplates().forEach { template ->
            template.write()
        }
    }

//
//    fun generatorCode(tableName: String) {
//        computeTargetTableDesc(tableName)
//            .filter { table -> context.tableFilters.all { it.allow(table) } }
//            .forEach { table -> generatorCode(table) }
//        context.reporter.printReport()
//    }
//
//    private fun computeTargetTableDesc(tableName: String): List<SelectTableDesc> {
//        sqlSessionFactory.mapperDo<TableMapper> { tableMapper ->
//            return if (tableName == ALL_TABLES) {
//                tableMapper.queryTableInfos()
//            } else {
//                tableMapper.queryTable(tableName)
//            }
//        }
//        return emptyList()
//    }
//
//    private fun generatorCode(selectTableDesc: SelectTableDesc) {
//        val table = selectTableDesc.fillToTable()
//        val velocityContext = toVelTableContext(table)
//        VmTemplate.values().forEach { template ->
//            if (!context.templateFilters.all { it.allow(table, template) }) {
//                return@forEach
//            }
//        }
//    }
//
//    private fun toVelTableContext(table: Table): VelocityContext {
//        val prop = Properties()
//        prop["file.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
//        Velocity.init(prop)
//        prop.putAll(generatorConfig.toMap())
//        prop.putAll(generatorConfig.extension)
//        prop.putAll(JSONObject.from(table))
//        prop["pathName"] = camelToSnake(table.classname)
//        return VelocityContext(prop)
//    }
//
//
//    private fun getOutputTemplateFilePath(template: VmTemplate, context: VelocityContext): Path {
//        val fileName = template.fileNameAction(context)
//        val srcPath = project.projectDir.toPath().resolve("src/main/java")
//        val packagePath = srcPath.resolve(context["mainPath"].toString().replace(".", File.separator))
//        val subpackage = template.subPackage.replace(".", File.separator)
//        val parentPath = packagePath.resolve(subpackage)
//        val parentDir = parentPath.toFile()
//        parentDir.exists() || parentDir.mkdirs()
//        return parentPath.resolve(fileName)
//    }
}

