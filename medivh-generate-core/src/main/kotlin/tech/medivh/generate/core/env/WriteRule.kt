package tech.medivh.generate.core.env

import tech.medivh.generate.core.Template


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface WriteRule {

    /**
     * the template is allowed to write
     * @return true if allowed
     */
    fun allow(template: Template): Boolean

    /**
     * if file is exist, whether to overwrite
     * @return true if allowed
     */
    fun overwrite(): Boolean
}
