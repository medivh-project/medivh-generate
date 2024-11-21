package tech.medivh.generate.core.provider.db


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class Column(
    val tableName: String,
    val columnName: String,
    val notNull: Boolean,
    val dataType: String,
    val columnKey: String,
    val columnComment: String?
)
