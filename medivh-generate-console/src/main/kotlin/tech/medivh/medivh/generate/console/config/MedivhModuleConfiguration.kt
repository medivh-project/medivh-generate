package tech.medivh.medivh.generate.console.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tech.medivh.generate.core.engine.ModuleLoader
import tech.medivh.medivh.generate.console.component.ConsoleModuleLoader


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Configuration
class MedivhModuleConfiguration {


    @Bean
    fun moduleLoader(): ConsoleModuleLoader {
        return ConsoleModuleLoader(ModuleLoader)
    }
}
