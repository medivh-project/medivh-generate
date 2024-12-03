package tech.medivh.generate.core.engine

import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.provider.TemplateProvider
import tech.medivh.generate.core.source.DataSourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface GeneratePlugin {

    /**
     * this function will auto invoke after instance created
     */
    fun setDataSource(dataSourceFacade: DataSourceFacade)

    fun pluginName(): String

    fun templateProvider(): TemplateProvider

    fun contextProvider(): ContextProvider

    /**
     * generate all file
     */
    fun generateAll()

    /**
     * generate table base file.
     * @param template This parameter is defined by the instance [GeneratePlugin]
     */
    fun generate(tableName: String, template: String)

}
