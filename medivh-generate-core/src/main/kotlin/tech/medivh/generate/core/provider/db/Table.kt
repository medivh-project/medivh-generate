package tech.medivh.generate.core.provider.db

import java.time.LocalDateTime

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class Table(
    val createTime: LocalDateTime,
    val engine: String,
    val tableName: String,
    val db: String,
    val comments: String?,
    val columns: MutableList<Column> = mutableListOf()
) {

}

