package tech.medivh.generate.core.format

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object CodeFormatterComposite : CodeFormatter {

    private val formatters = mutableListOf<CodeFormatter>()

    init {
        addFormatter(JavaCodeFormatter())
    }

    fun addFormatter(formatter: CodeFormatter) {
        formatters.add(formatter)
    }

    override fun format(code: String, file: File): String {
        return formatters.firstOrNull { it.support(file) }?.format(code, file) ?: run {
            System.err.println("not support file type ${file.name}")
            code
        }
    }

    override fun support(file: File): Boolean {
        return formatters.any { it.support(file) }
    }
}
