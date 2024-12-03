package tech.medivh.generate.core.format

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface CodeFormatter {

    /**
     * @param code text
     */
    fun format(code: String, file: File): String

    /**
     * support format file type
     */
    fun support(file: File): Boolean
}
