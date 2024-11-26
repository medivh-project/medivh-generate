package tech.medivh.generate.core

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface FileWriter {

    /**
     * write to file
     */
    fun write(): File
}
