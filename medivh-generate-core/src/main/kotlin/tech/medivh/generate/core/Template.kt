package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.TemplateContext
import tech.medivh.generate.core.event.NotAllowEvent
import tech.medivh.generate.core.event.WriteEvent
import java.io.StringWriter


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Template(private val context: TemplateContext) {

    fun write() {
        if (!context.allow()) {
            return context.publishEvent(NotAllowEvent(context))
        }
        StringWriter().use {
            check(context.vmFile.exists())
            val tpl = Velocity.getTemplate(context.vmFile.path, "UTF-8")
            tpl.merge(context, it)
            val targetFile = context.targetFile(context)
            if (!targetFile.exists()) {
                targetFile.writeText(it.toString())
                context.publishEvent(WriteEvent(context))
                return
            }
            if (context.overwrite()) {
                TODO("写入")
            } else {
                TODO("跳过")
            }
        }
    }

    override fun toString(): String {
        return "Template: $context"
    }
}
