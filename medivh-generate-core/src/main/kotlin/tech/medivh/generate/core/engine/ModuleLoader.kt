package tech.medivh.generate.core.engine


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object ModuleLoader {

    private val modules = mutableListOf<GeneratePlugin>()

    fun installModule(module: GeneratePlugin) {
        modules.add(module)
    }

    /**
     * At present, there is only one way to register, and there may be many more in the future
     */
    fun loadModules(): List<GeneratePlugin> {
        return modules.toList()
    }
}
