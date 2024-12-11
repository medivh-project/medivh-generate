package tech.medivh.generate.core.rule

import tech.medivh.generate.core.Template
import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.env.GeneratorContext
import java.io.File
import java.nio.file.Paths

/**
 * @author gongxuanzhangmelt@gmail.com
 */
object DesktopWriteRule : WriteRule {

    private val desktopDir = getDesktopFolder()

    override fun overwrite(): Boolean {
        return true
    }

    override fun targetFile(template: Template, context: GeneratorContext): File {
        return desktopDir.resolve("${context["className"]}${template.templateName().substringBeforeLast(".")}")
    }


    override fun format(): Boolean {
        return true
    }

    override fun zip(): Boolean {
        return true
    }

    private fun getDesktopFolder(): File {
        val userHome = System.getProperty("user.home")
        Paths.get(userHome, "Desktop").toFile().let {
            if (!it.exists()) {
                it.mkdirs()
            }
            return it
        }
    }

}
