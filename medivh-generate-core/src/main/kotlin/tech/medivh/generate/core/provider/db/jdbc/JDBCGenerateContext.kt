package tech.medivh.generate.core.provider.db.jdbc

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import tech.medivh.generate.core.env.BaseGenerateContext
import tech.medivh.generate.core.provider.db.Table
import tech.medivh.generate.core.provider.db.TableProperties


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JDBCGenerateContext(table: Table) : BaseGenerateContext(table) {
    init {
        this.putAll(JSON.toJSON(TableProperties(table)) as JSONObject)
    }

}
