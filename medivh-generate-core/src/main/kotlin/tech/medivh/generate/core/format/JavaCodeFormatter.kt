package tech.medivh.generate.core.format

import com.google.googlejavaformat.java.Formatter
import java.io.File


/**
 *
 * delegate to google java code formatter
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaCodeFormatter : CodeFormatter {

    private val googleFormatter by lazy { Formatter() }

    private fun googleFormat(javaCode: String): String {
        try {
            return googleFormatter.formatSource(javaCode)
        } catch (e: Exception) {
            System.err.println("format java code error ${e.message} $javaCode")
            return javaCode
        }
    }

    override fun format(code: String, file: File): String {
        return googleFormat(code)
    }

    override fun support(file: File): Boolean {
        return file.extension == "java"
    }
}
