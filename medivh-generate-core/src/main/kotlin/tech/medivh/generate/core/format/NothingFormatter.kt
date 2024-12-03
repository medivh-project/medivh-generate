package tech.medivh.generate.core.format

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object NothingFormatter : CodeFormatter {

    override fun format(code: String, file: File): String {
        return code
    }

    override fun support(file: File): Boolean {
        return true
    }
}
