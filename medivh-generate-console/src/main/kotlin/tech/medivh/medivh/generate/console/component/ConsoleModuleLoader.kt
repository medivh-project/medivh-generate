package tech.medivh.medivh.generate.console.component

import jakarta.annotation.PostConstruct
import tech.medivh.generate.core.engine.ModuleLoader
import tech.medivh.generate.plugin.mybatisplus.MybatisPlusPlugin


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class ConsoleModuleLoader(private val moduleLoader: ModuleLoader) {

    @PostConstruct
    private fun init() {
        moduleLoader.installModule(MybatisPlusPlugin())
    }

    fun getModule(moduleName: String) = moduleLoader.modules().firstOrNull { it.pluginName() == moduleName }


}
