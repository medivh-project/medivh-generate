package tech.medivh.generate.core

import java.net.URL


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class URLTemplate(private val resource: URL) : Template {

    override fun resource(): URL {
        return resource
    }
}
