package tech.medivh.generate.core

import tech.medivh.generate.core.env.GeneratorContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface WriteRule {


    /**
     * if file is exist, whether to overwrite
     * @return true if allowed
     */
    fun overwrite(): Boolean

    /**
     * a target file to write
     */
    fun targetFile(template: Template, context: GeneratorContext): File

    fun format(): Boolean

}
