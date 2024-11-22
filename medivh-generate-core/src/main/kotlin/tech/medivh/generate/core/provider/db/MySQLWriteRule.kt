package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLWriteRule :WriteRule {

    override fun allow(template: TemplateContext): Boolean {
        return true
    }

    override fun overwrite(): Boolean {
        return false
    }

    override fun targetFile(template: TemplateContext): File {
        return File("aaa.txt")
    }
}
