package tech.medivh.generate.core.source

import tech.medivh.generate.core.provider.db.Table

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MongoDBDataSource(configuration: MongoConfiguration) : DataSourceFacade {

    override fun testConnection() {
        TODO("Not yet implemented")
    }

    override fun getTables(): List<Table> {
        TODO("Not yet implemented")
    }
}