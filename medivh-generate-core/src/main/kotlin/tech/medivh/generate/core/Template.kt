package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GlobalProperties
import tech.medivh.generate.core.env.TemplateContext
import tech.medivh.generate.core.event.BeforeCoverEvent
import tech.medivh.generate.core.event.NotAllowEvent
import tech.medivh.generate.core.event.SkipFileEvent
import tech.medivh.generate.core.event.WriteEvent
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
            val prop = Properties()
            prop["file.resource.loader.class"] = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
            Velocity.init(prop)
            val tpl = Velocity.getTemplate(
                "${GlobalProperties.DEFAULT_TEMPLATE_DIR}/${context.vmFileName}",
                GlobalProperties.DEFAULT_TEMPLATE_ENCODING
            )
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
