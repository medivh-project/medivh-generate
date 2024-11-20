package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.filter.TableFilter
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.filter.TemplateFilter
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class GeneratorContext {

    val tableFilters = mutableListOf<TableFilter>()

    val templateFilters = mutableListOf<TemplateFilter>()

    lateinit var reporter: GenerateReporter

    fun addTableFilter(filter: TableFilter) {
        tableFilters.add(filter)
    }

    fun addTemplateFilter(filter: TemplateFilter) {
        templateFilters.add(filter)
    }

    fun setBakDir(bakDir: File) {
        val currentBak = run {
            val prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            if (!bakDir.exists()) {
                bakDir.resolve("$prefix 1").apply { mkdirs() }
            }
            val listFiles = bakDir.listFiles { _, name -> name.startsWith(prefix) }
            if (listFiles.isNullOrEmpty()) {
                bakDir.resolve("$prefix 1").apply { mkdirs() }
            }
            val last = listFiles.last()
            var index = last.name.substringAfterLast(" ").toInt()
            var currentBakDir = bakDir.resolve("$prefix ${++index}")
            while (currentBakDir.exists()) {
                currentBakDir = bakDir.resolve("$prefix ${++index}")
            }
            currentBakDir
        }
        reporter = GenerateReporter(currentBak)

    }

}