package tech.medivh.generate.core.provider.build.java

import com.google.googlejavaformat.java.Formatter


/**
 *
 * delegate to google java code formatter
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object JavaCodeFormatter {

    private val googleFormatter by lazy { Formatter() }

    fun googleFormat(javaCode: String): String {
        return googleFormatter.formatSource(javaCode)
    }
}
