package tech.medivh.generate.core.provider.db

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.varchar
import java.time.LocalDateTime

typealias MedivhTable = tech.medivh.generate.core.provider.db.Table

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object Tables : Table<TableDesc>("tables") {
    val createTime = datetime("CREATE_TIME").bindTo { it.createTime }
    val engine = varchar("ENGINE").bindTo { it.engine }
    val name = varchar("TABLE_NAME").bindTo { it.tableName }
    val db = varchar("TABLE_SCHEMA").bindTo { it.db }
    val comments = varchar("TABLE_COMMENT").bindTo { it.comments }
}

interface TableDesc : Entity<TableDesc> {
    val createTime: LocalDateTime
    val engine: String
    val tableName: String
    val db: String
    val comments: String?

    fun table(): MedivhTable {
        return MedivhTable(createTime, engine, tableName, db, comments)
    }
}

object Columns : Table<ColumnDesc>("columns", schema = "information_schema") {
    val name = varchar("TABLE_NAME").bindTo { it.tableName }
    val db = varchar("TABLE_SCHEMA")
    val columnName = varchar("COLUMN_NAME").bindTo { it.columnName }
    val nullable = varchar("IS_NULLABLE").bindTo { it.nullable }
    val dataType = varchar("DATA_TYPE").bindTo { it.dataType }
    val columnKey = varchar("COLUMN_KEY").bindTo { it.columnKey }
    val columnComment = varchar("COLUMN_COMMENT").bindTo { it.columnComment }
}

interface ColumnDesc : Entity<ColumnDesc> {
    val tableName: String
    val columnName: String
    val nullable: String
    val dataType: String
    val columnKey: String
    val columnComment: String?

    fun column(): Column {
        return Column(tableName, columnName, nullable == "NO", dataType, columnKey, columnComment)
    }
}
