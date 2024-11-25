package tech.medivh.generate.core.provider.db

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import tech.medivh.generate.core.event.BeforeMergeTemplateEvent
import tech.medivh.generate.core.event.EventListener


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLFillListener : EventListener<BeforeMergeTemplateEvent> {

    override fun onEvent(event: BeforeMergeTemplateEvent) {
        val mySqlContext = event.context as? MySQLTemplateContext ?: return
        val tableProperties = resolveProperties(mySqlContext)
        mySqlContext.putAll(JSON.toJSON(tableProperties) as JSONObject)
    }


    private fun resolveProperties(context: MySQLTemplateContext): TableProperties {
        return TableProperties(context.table)
    }
}
