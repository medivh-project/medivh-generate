package tech.medivh.generate.core.env

import org.apache.velocity.context.Context
import tech.medivh.generate.core.event.Bus
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface TemplateContext : Context, Bus {

    /**
     * the velocity template file
     */
    val vmFile: File

    fun allow(): Boolean

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
