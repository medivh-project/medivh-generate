package tech.medivh.generate.core.provider.db

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import tech.medivh.generate.core.env.BaseGenerateContext
import tech.medivh.generate.core.env.GeneratorContext
import java.time.LocalDateTime

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class Table(
    val createTime: LocalDateTime,
    val engine: String,
    val tableName: String,
    val db: String,
    val comment: String?,
    val columns: MutableList<Column> = mutableListOf()
) {
    fun toGenerateContext(): GeneratorContext {
        val json = JSON.toJSON(TableProperties(this)) as JSONObject
        return BaseGenerateContext().apply { putAll(json) }
    }
}

