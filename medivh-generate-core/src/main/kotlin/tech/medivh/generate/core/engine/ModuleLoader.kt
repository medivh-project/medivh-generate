package tech.medivh.generate.core.engine


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object ModuleLoader {

    private val modules = mutableListOf<GenerateModule>()

    fun installModule(module: GenerateModule) {
        modules.add(module)
    }

    /**
     * At present, there is only one way to register, and there may be many more in the future
     */
    fun loadModules(): List<GenerateModule> {
        return modules.toList()
    }
}
