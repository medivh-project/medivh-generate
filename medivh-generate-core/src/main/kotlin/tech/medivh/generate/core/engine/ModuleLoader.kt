package tech.medivh.generate.core.engine

import org.slf4j.LoggerFactory


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object ModuleLoader {

    private val modules = mutableListOf<GeneratePlugin>()

    private val log = LoggerFactory.getLogger(ModuleLoader::class.java)

    fun installModule(module: GeneratePlugin) {
        modules.add(module)
        log.info("install module: ${module.pluginName()}")
    }

    /**
     * At present, there is only one way to register, and there may be many more in the future
     */
    fun modules(): List<GeneratePlugin> {
        return modules.toList()
    }


}
