package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.event.BeforeMergeTemplateEvent
import tech.medivh.generate.core.event.EventListener


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLFillListener : EventListener<BeforeMergeTemplateEvent> {

    override fun onEvent(event: BeforeMergeTemplateEvent) {
        val mySqlContext = event.context as? MySQLTemplateContext ?: return
        mySqlContext.table

    }
}
