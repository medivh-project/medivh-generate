package tech.medivh.generate.core.source

import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.db.Table


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface DataSourceFacade : ContextProvider {

    /**
     * data source available
     */
    fun testConnection()

    /**
     * database source
     */
    fun getTables(): List<Table>

    override fun computeContext(): List<GeneratorContext> {
        return getTables().map { it.toGenerateContext() }
    }
}
