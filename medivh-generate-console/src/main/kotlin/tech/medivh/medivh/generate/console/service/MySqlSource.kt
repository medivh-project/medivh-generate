package tech.medivh.medivh.generate.console.service

import tech.medivh.generate.core.provider.db.Table
import tech.medivh.medivh.generate.console.core.SourceFacade
import tech.medivh.medivh.generate.console.component.MySqlConfiguration

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MySqlSource(val configuration: MySqlConfiguration) : SourceFacade {

    override fun testConnection() {
        TODO("Not yet implemented")
    }

    override fun getTables(): List<Table> {
        TODO("Not yet implemented")
    }


}