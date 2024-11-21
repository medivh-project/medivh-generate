package tech.medivh.generate.core

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class GenerateReporter(val bakDir: File) {

    private val ignoreFiles = mutableListOf<File>()

    private val coverFiles = mutableListOf<File>()

    fun reportIgnore(file: File) {
        ignoreFiles.add(file)
    }

    fun reportCover(file: File) {
        coverFiles.add(file)
    }

    fun printReport() {
        if (ignoreFiles.isNotEmpty()) {
            println("共有以下${ignoreFiles.size}个文件已经存在,被跳过")
            println(ignoreFiles.map { it.name })
        }
        if (coverFiles.isNotEmpty()) {
            println("共有以下${coverFiles.size}个文件被覆盖")
            println(coverFiles.map { it.name })
            println("被覆盖的文件在${bakDir.absolutePath}下")
        }

    }
}
