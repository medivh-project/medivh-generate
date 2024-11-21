package tech.medivh.generate.core.env

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface WriteRule {

    /**
     * the template is allowed to write
     * @return true if allowed
     */
    fun allow(template: TemplateContext): Boolean

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
