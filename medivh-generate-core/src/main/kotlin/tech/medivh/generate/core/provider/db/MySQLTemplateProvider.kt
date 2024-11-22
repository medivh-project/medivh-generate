package tech.medivh.generate.core.provider.db

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.groupBy
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf
import tech.medivh.generate.core.Template
import tech.medivh.generate.core.provider.TemplateProvider
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLTemplateProvider(val config: MySQLConfiguration) : TemplateProvider {

    private val database = Database.connect(
        url = config.url,
        driver = config.driver,
        user = config.user,
        password = config.password
    )

    private val Database.tables get() = this.sequenceOf(Tables)
    private val Database.columns get() = this.sequenceOf(Columns)

    override fun getTemplates(): List<Template> {
        val columns = database.columns.filter { it.db eq database.name }.groupBy({ it.tableName }, { it.column() })
        val tableInfos = database.tables
            .filter { it.db eq database.name }
            .map { it.table().apply { this.columns.addAll(columns[it.tableName] ?: emptyList()) } }
            .toList()
        // todo  how to find template file ?
        val templateDir = javaClass.classLoader.getResource("templates/")?.file?.let { File(it) }
            ?: throw IllegalStateException("template dir not found")

        return templateDir.listFiles { file -> file.extension == "vm" }
            ?.flatMap { vmFile -> tableInfos.map { MySQLTemplateContext(vmFile, it) } }
            ?.map { Template(config.createWriteRule(it), it) }
            ?.toList() ?: emptyList()
    }


}
