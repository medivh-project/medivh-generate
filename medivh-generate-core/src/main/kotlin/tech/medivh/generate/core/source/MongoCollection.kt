package tech.medivh.generate.core.source

import tech.medivh.generate.core.provider.db.Table
import java.time.LocalDateTime


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MongoCollection(
    val tableName: String,
    val db: String,
    val engine: String,
    val comment: String?,
    val columns: MutableList<MongoColumn> = mutableListOf()
) {
    fun adaptToTable(): Table {
        return Table(
            createTime = LocalDateTime.now(),
            engine = engine,
            comment = comment,
            tableName = tableName,
            db = db,
            columns = columns.map { it.adaptToColum(this) }.toMutableList()
        )
    }
}

