package tech.medivh.medivh.generate.console.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.medivh.medivh.generate.console.annotation.GenerateSource
import tech.medivh.medivh.generate.console.core.DataSource
import tech.medivh.medivh.generate.console.core.SourceFacade
import tech.medivh.medivh.generate.console.core.Result

/**
 * @author gongxuanzhangmelt@gmail.com
 */
@RestController
@RequestMapping("/generate")
class GenerateController {

    @PostMapping("/test_connection/{strategy}")
    fun testConnection(
        @PathVariable("strategy") strategy: DataSource,
        @GenerateSource dataSource: SourceFacade
    ): Result<String> {
        try {
            dataSource.testConnection()
        } catch (e: Exception) {
            Result.fail(message = e.message)
        }
        return Result.success("success")
    }

}