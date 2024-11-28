package tech.medivh.generate.core

import org.apache.velocity.app.Velocity
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.event.*
import tech.medivh.generate.core.provider.build.java.JavaCodeFormatter
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
            Velocity.evaluate(context, write, template.templateName(), template.readText())
            val targetFile = rule.targetFile(template, context)
            val finalCode = if (rule.format()) {
                JavaCodeFormatter.googleFormat(write.toString())
            } else {
                write.toString()
            }

            if (targetFile.exists().not()) {
                targetFile.writeText(finalCode)
                publishEvent(WriteEvent(targetFile))
                return targetFile
            }

            if (rule.overwrite()) {
                publishEvent(BeforeCoverEvent(targetFile, finalCode))
                targetFile.writeText(finalCode)
                return targetFile
            }
            publishEvent(TargetFileExistEvent(targetFile))
            return targetFile
        }

    }


}

