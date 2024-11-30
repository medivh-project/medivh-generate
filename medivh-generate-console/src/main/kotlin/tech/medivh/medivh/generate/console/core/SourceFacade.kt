package tech.medivh.medivh.generate.console.core

import tech.medivh.generate.core.provider.db.Table

/**
 * @author gongxuanzhangmelt@gmail.com
 */
interface SourceFacade {

    /**
     * data source available
     */
    fun testConnection()

    /**
     * database source
     */
    fun getTables(): List<Table>
}