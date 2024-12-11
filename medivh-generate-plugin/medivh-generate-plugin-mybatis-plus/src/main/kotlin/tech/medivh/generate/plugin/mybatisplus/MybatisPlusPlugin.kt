package tech.medivh.generate.plugin.mybatisplus

import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.engine.GeneratePlugin
import tech.medivh.generate.core.engine.Generator
import tech.medivh.generate.core.provider.TemplateProvider
import tech.medivh.generate.core.source.DataSourceFacade
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MybatisPlusPlugin : GeneratePlugin {

    private val templateProvider = MyBatisPlusTemplateProvider()

    var config: MyBatisPlusGenerateConfig = defaultConfig

    private lateinit var dataSourceFacade: DataSourceFacade

    override fun setDataSource(dataSourceFacade: DataSourceFacade) {
        this.dataSourceFacade = dataSourceFacade
    }

    override fun pluginName(): String {
        return "mybatis-plus"
    }

    override fun templateProvider(): TemplateProvider {
        return templateProvider
    }

    override fun contextProvider(): ContextProvider {
        return MyBatisPlusContextProvider(config, dataSourceFacade)
    }

    override fun generateAll(): File {
        return Generator(contextProvider(), templateProvider).generate()
    }

    override fun generate(tableName: String, template: String) {
        TODO("Not yet implemented")
    }
}
