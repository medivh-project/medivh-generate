package tech.medivh.generate.core.provider.db

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.groupBy
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf
import tech.medivh.generate.core.Template
import tech.medivh.generate.core.provider.TemplateProvider


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
        val tableInfos = database.tables.filter { it.db eq database.name }.map { it }
        val columns = database.columns.filter { it.db eq database.name }.groupBy { it.tableName }
        TODO()
    }


}
