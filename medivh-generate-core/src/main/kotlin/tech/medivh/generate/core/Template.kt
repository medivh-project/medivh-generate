package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GlobalProperties
import tech.medivh.generate.core.env.TemplateContext
import tech.medivh.generate.core.event.*
import java.io.StringWriter
import java.util.*


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class Template(private val context: TemplateContext) {

    fun write() {
        if (!context.allow()) {
            return context.publishEvent(NotAllowEvent(context))
        }
        StringWriter().use {
            val tpl = Velocity.getTemplate(
                "${GlobalProperties.DEFAULT_TEMPLATE_DIR}/${context.vmFileName}",
                GlobalProperties.DEFAULT_TEMPLATE_ENCODING
            )
            context.publishEvent(BeforeMergeTemplateEvent(context))
            tpl.merge(context, it)
            val targetFile = context.targetFile(context)
            if (!targetFile.exists()) {
                targetFile.writeText(it.toString())
                return context.publishEvent(WriteEvent(context))
            }
            if (context.overwrite()) {
                context.publishEvent(BeforeCoverEvent(targetFile, context))
                return targetFile.writeText(it.toString())
            }
            context.publishEvent(SkipFileEvent(targetFile, context))
        }
    }

    override fun toString(): String {
        return "Template: $context"
    }

}
