package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.TableMapper
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.mapperDo
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.snakeToCamel
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.sqlSessionFactory
import org.gradle.kotlin.dsl.support.uppercaseFirstChar

/**
 * @author gongxuanzhangmelt@gmail.com
 */
data class SelectTableDesc(
    val tableName: String,
    val engine: String,
    val tableComment: String?,
    val createTime: String
) {

    /**
     * The number of loops is increased to make the code easier to read.
     * The loss of performance is acceptable in this scenario
     */
    fun fillToTable(): Table {
        sqlSessionFactory.mapperDo<TableMapper> { tableMapper ->
            val columns = tableMapper.queryColumns(tableName).map { it.fillToColumn() }
            return Table(
                tableName = tableName,
                comments = tableComment,
                pk = columns.find { it.pk } ?: columns.first(),
                columns = columns,
                className = snakeToCamel(tableName).uppercaseFirstChar(),
                classname = snakeToCamel(tableName),
                relationTable = columns.filter { it.pk }.size > 1 || columns.none { it.pk },
                hasBigDecimal = columns.any { it.attrType == "BigDecimal" },
                hasLocalDateTime = columns.any { it.attrType == "LocalDateTime" },
                hasInstant = columns.any { it.attrType == "Instant" }
            )
        }
        throw IllegalStateException("Can't find table $tableName")
    }

}
