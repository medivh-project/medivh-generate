package tech.medivh.generate.core.source

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.groupBy
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tech.medivh.generate.core.provider.db.Columns
import tech.medivh.generate.core.provider.db.Table
import tech.medivh.generate.core.provider.db.Tables


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLDataSource(private val configuration: MySqlConfiguration) : DataSourceFacade {

    private val database: Database by lazy {
        Database.connect(
            url = "jdbc:mysql://${configuration.host}:${configuration.port}/${configuration.database}?useUnicode=true&characterEncoding=utf8",
            driver = configuration.driver,
            user = configuration.user,
            password = configuration.password
        )
    }

    private val Database.tables get() = this.sequenceOf(Tables)
    private val Database.columns get() = this.sequenceOf(Columns)

    override fun testConnection() {
        log.info("test connection $database")
    }

    override fun getTables(): List<Table> {
        val columns = database.columns.filter { it.db eq database.name }.groupBy({ it.tableName }, { it.column() })
        return database.tables
            .filter { it.db eq database.name }
            .map { it.table().apply { this.columns.addAll(columns[it.tableName] ?: emptyList()) } }
            .toList()
    }

    companion object {
        var log: Logger = LoggerFactory.getLogger(MySQLDataSource::class.java)
    }


}
