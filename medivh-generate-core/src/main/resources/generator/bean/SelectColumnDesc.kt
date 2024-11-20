package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean


import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.generatorConfig
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.snakeToCamel
import org.gradle.kotlin.dsl.support.uppercaseFirstChar

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class SelectColumnDesc(
    val columnName: String,
    val dataType: String,
    val columnComment: String,
    val columnKey: String,
    val extra: String,
) {

    fun fillToColumn(): Column {
        val attrType = generatorConfig.type[dataType]
        return Column(
            columnName = columnName,
            dataType = dataType,
            comments = columnComment,
            extra = extra,
            attrName = snakeToCamel(columnName).uppercaseFirstChar(),
            attrname = snakeToCamel(columnName),
            attrType = attrType,
            canEquals = canEquals(attrType, columnName),
            pk = "PRI" == columnKey
        )
    }

    fun toGeneratorInfo(content: MutableMap<String, Any>): Column {
        val attrType = generatorConfig.type[dataType]
        when (attrType) {
            "BigDecimal" -> content["hasBigDecimal"] = true
            "LocalDateTime" -> content["hasLocalDateTime"] = true
            "Instant" -> content["hasInstant"] = true
        }
        return Column(
            columnName = columnName,
            dataType = dataType,
            comments = columnComment,
            extra = extra,
            attrName = snakeToCamel(columnName).uppercaseFirstChar(),
            attrname = snakeToCamel(columnName),
            attrType = attrType,
            canEquals = canEquals(attrType, columnName),
        )
    }

    private fun canEquals(attrType: String?, columnName: String): Boolean {
        if (ignoreEqualsColumnName.contains(columnName)) {
            return false
        }
        return canEqualsType.contains(attrType)
    }

    companion object {
        val ignoreEqualsColumnName = setOf("create_by", "update_by", "remark")
        val canEqualsType = setOf("String", "Int", "Long", "Boolean", "BigDecimal")
    }
}
