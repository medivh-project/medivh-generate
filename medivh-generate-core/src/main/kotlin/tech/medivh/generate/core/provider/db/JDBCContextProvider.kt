package tech.medivh.generate.core.provider.db

import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.groupBy
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf
import tech.medivh.generate.core.ContextExtension
import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.EnlightenedContextExtension
import tech.medivh.generate.core.env.GeneratorContext


/**
 *
 * @param database connection instance
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JDBCContextProvider(
    private val database: Database,
    private val tableFilter: TableFilter = EnlightenedTableFilter,
    private val contextExtension: ContextExtension = EnlightenedContextExtension
) : ContextProvider {

    private val Database.tables get() = this.sequenceOf(Tables)
    private val Database.columns get() = this.sequenceOf(Columns)

    override fun computeContext(): List<GeneratorContext> {
        val columns = database.columns.filter { it.db eq database.name }.groupBy({ it.tableName }, { it.column() })
        return database.tables
            .filter { it.db eq database.name }
            .map { it.table().apply { this.columns.addAll(columns[it.tableName] ?: emptyList()) } }
            .mapNotNull { context(it) }
            .map { contextExtension.doExtend(it) }
            .toList()
    }


    private fun context(table: Table): GeneratorContext? {
        return table.takeIf { tableFilter.allow(it) }?.let { JDBCGenerateContext(it) }
    }
}
