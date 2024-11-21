package tech.medivh.generate.core.env

import org.apache.velocity.context.Context
import tech.medivh.generate.core.event.EventPublisher
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface TemplateContext : Context {

    /**
     * the velocity template file
     */
    val vmFile: File

    val bus: EventPublisher

}
