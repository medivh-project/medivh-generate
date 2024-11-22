package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.TemplateContext
import tech.medivh.generate.core.event.WriteRuleNotAllowEvent
import java.io.StringWriter


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Template(private val rule: WriteRule, private val context: TemplateContext) {


    fun write() {
        if (!rule.allow(context)) {
            return context.bus.publishEvent(WriteRuleNotAllowEvent(context, rule))
        }
        StringWriter().use {
            check(context.vmFile.exists())
            val tpl = Velocity.getTemplate(context.vmFile.path, "UTF-8")
            tpl.merge(context, it)
            val targetFile = rule.targetFile(context)
            if (!targetFile.exists()) {
                targetFile.writeText(it.toString())
                TODO("event")
                return
            }
            if (rule.overwrite()) {
                TODO("写入")
            } else {
                TODO("跳过")
            }
        }
    }

    override fun toString(): String {
        return "vm file: ${context.vmFile.name}"
    }
}
