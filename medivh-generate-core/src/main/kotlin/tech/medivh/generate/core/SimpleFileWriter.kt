package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.event.*
import java.io.File
import java.io.StringWriter


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class SimpleFileWriter(
    private val template: Template,
    private val context: GeneratorContext,
    private val rule: WriteRule,
    eventPublisher: EventPublisher = EventPublisher()
) : FileWriter, Bus by eventPublisher {


    override fun write(): File {
        StringWriter().use { write ->
            Velocity.evaluate(context, write, template.resource().file, template.resource().readText())
            val targetFile = rule.targetFile(template, context)
            if (targetFile.exists().not()) {
                targetFile.writeText(write.toString())
                publishEvent(WriteEvent(targetFile))
                return targetFile
            }

            if (rule.overwrite()) {
                publishEvent(BeforeCoverEvent(targetFile, write.toString()))
                targetFile.writeText(write.toString())
                return targetFile
            }
            publishEvent(TargetFileExistEvent(targetFile))
            return targetFile
        }

    }

}
