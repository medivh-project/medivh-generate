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

    private var errorPrint = false

    private fun googleFormat(javaCode: String): String {
        try {
            return googleFormatter.formatSource(javaCode)
        } catch (e: Exception) {
            System.err.println("format java code error ${e.message} $javaCode")
            return javaCode
        } catch (accessE: IllegalAccessError) {
            if (!errorPrint) {
                System.err.println(
                    "google format need jvm options: \n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED\n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED\n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED\n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED\n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED\n" +
                            "--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED\n" +
                            "see https://github.com/google/google-java-format"
                )
                errorPrint = true
            }

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
