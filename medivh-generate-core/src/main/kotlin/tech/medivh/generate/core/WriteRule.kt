package tech.medivh.generate.core

import tech.medivh.generate.core.annotation.Reserved
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Reserved
interface WriteRule {


    /**
     * if file is exist, whether to overwrite
     * @return true if allowed
     */
    fun overwrite(): Boolean

    /**
     * a target file to write
     */
    fun targetFile(template: TemplateContext): File
}
