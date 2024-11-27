package tech.medivh.generate.core.provider.build.java

import com.google.googlejavaformat.java.Formatter


/**
 *
 * delegate to google java code formatter
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object JavaFormatter {

    fun formatCode(javaCode: String): String {
        return Formatter().formatSource(javaCode)
    }
}
