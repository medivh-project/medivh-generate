package tech.medivh.generate.core.provider.db

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import tech.medivh.generate.core.env.AbstractGenerateContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JDBCGenerateContext(table: Table) : AbstractGenerateContext() {
    init {
        this.putAll(JSON.toJSON(TableProperties(table)) as JSONObject)
    }
}
