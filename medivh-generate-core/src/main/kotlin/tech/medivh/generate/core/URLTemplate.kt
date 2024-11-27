package tech.medivh.generate.core

import java.io.File
import java.net.URL


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class URLTemplate(private val resource: URL) : Template {


    override fun templateName(): String {
        return File(resource.file).name
    }

    override fun readText(): String {
        return resource.readText()
    }
}
