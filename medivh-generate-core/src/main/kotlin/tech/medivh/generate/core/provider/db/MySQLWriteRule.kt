package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.annotation.Reserved
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Reserved
class MySQLWriteRule(
    private val overwrite: Boolean,
    private val targetFileAction: (TemplateContext) -> File
) : WriteRule {

    override fun overwrite(): Boolean {
        return overwrite
    }

    override fun targetFile(template: TemplateContext): File {
        return targetFileAction.invoke(template)
    }
}
