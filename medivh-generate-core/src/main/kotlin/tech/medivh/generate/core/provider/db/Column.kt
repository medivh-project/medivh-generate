package tech.medivh.generate.core.provider.db


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class Column(
    val tableName: String,
    val name: String,
    val notNull: Boolean,
    val pk: Boolean,
    val dataType: String,
    val comment: String?
)
