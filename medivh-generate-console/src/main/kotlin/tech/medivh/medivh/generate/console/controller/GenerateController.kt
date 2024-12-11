package tech.medivh.medivh.generate.console.controller

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.medivh.generate.core.provider.db.Table
import tech.medivh.generate.core.source.DataSourceFacade
import tech.medivh.generate.core.source.SourceType
import tech.medivh.medivh.generate.console.annotation.GenerateSource
import tech.medivh.medivh.generate.console.component.ConsoleModuleLoader
import tech.medivh.medivh.generate.console.component.DataSourceResolver
import tech.medivh.medivh.generate.console.component.SourceManager
import tech.medivh.medivh.generate.console.core.Result


/**
 * @author gongxuanzhangmelt@gmail.com
 */
@RestController
@RequestMapping("/generate")
class GenerateController(
    resolver: List<DataSourceResolver>,
    private val sourceManager: SourceManager,
    private val moduleLoader: ConsoleModuleLoader
) {

    val dataSourceResolverMap = resolver.associateBy { it.support() }

    @PostMapping("/test_connection/{strategy}")
    fun testConnection(
        @PathVariable("strategy") strategy: SourceType,
        @RequestBody json: Map<String, Any>
    ): Result<String> {
        try {
            val source = dataSourceResolverMap[strategy]?.resolve(json)
                ?: throw IllegalArgumentException("unsupported strategy $strategy")
            source.testConnection()
            sourceManager.registerSource(source)
            return Result.success()
        } catch (e: Exception) {
            return Result.fail(message = e.message)
        }
    }


    @GetMapping("/tables")
    fun getTable(@GenerateSource sourceFacade: DataSourceFacade): Result<List<Table>> {
        return Result.success(sourceFacade.getTables())
    }

    @PostMapping("/generate_all/{pluginName}")
    fun generateCode(
        @GenerateSource sourceFacade: DataSourceFacade,
        @PathVariable(value = "pluginName") pluginName: String
    ): ResponseEntity<Resource> {
        val source = sourceManager.getSource()
        val module =
            moduleLoader.getModule(pluginName) ?: throw IllegalArgumentException("plugin $pluginName not found")
        module.setDataSource(source)
        val zip = module.generateAll()

        if (!zip.exists()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${zip.name}\"")
            .body(FileSystemResource(zip))

    }

}
