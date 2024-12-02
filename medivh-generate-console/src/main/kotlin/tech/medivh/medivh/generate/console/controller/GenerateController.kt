package tech.medivh.medivh.generate.console.controller

import org.springframework.web.bind.annotation.*
import tech.medivh.generate.core.provider.db.Table
import tech.medivh.medivh.generate.console.annotation.GenerateSource
import tech.medivh.medivh.generate.console.component.DataSourceResolver
import tech.medivh.medivh.generate.console.component.SourceManager
import tech.medivh.generate.core.source.SourceType
import tech.medivh.medivh.generate.console.core.Result
import tech.medivh.medivh.generate.console.core.SourceFacade

/**
 * @author gongxuanzhangmelt@gmail.com
 */
@RestController
@RequestMapping("/generate")
class GenerateController(
    resolver: List<DataSourceResolver>,
    private val sourceManager: SourceManager
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
    fun getTable(@GenerateSource sourceFacade: SourceFacade): Result<List<Table>> {
        return Result.success(sourceFacade.getTables())
    }

}
