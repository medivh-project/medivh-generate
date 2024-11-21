package tech.medivh.generate.core.env

import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.provider.TemplateProvider


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class GeneratorContext {

    val templateProvider: TemplateProvider = TODO()

    val writeRule: WriteRule = TODO()

//    val tableFilters = mutableListOf<TableFilter>()
//
//    val templateFilters = mutableListOf<TemplateFilter>()
//
//
//
//    lateinit var reporter: GenerateReporter
//
//    fun addTableFilter(filter: TableFilter) {
//        tableFilters.add(filter)
//    }
//
//    fun addTemplateFilter(filter: TemplateFilter) {
//        templateFilters.add(filter)
//    }
//
//    fun setBakDir(bakDir: File) {
//        val currentBak = run {
//            val prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//            if (!bakDir.exists()) {
//                bakDir.resolve("$prefix 1").apply { mkdirs() }
//            }
//            val listFiles = bakDir.listFiles { _, name -> name.startsWith(prefix) }
//            if (listFiles.isNullOrEmpty()) {
//                bakDir.resolve("$prefix 1").apply { mkdirs() }
//            }
//            val last = listFiles.last()
//            var index = last.name.substringAfterLast(" ").toInt()
//            var currentBakDir = bakDir.resolve("$prefix ${++index}")
//            while (currentBakDir.exists()) {
//                currentBakDir = bakDir.resolve("$prefix ${++index}")
//            }
//            currentBakDir
//        }
//        reporter = GenerateReporter(currentBak)
//
//    }

}
