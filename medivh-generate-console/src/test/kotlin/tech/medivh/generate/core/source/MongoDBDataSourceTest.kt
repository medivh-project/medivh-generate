package tech.medivh.generate.core.source

import org.junit.jupiter.api.Test


class MongoDBDataSourceTest {

    @Test
    fun testTestConnection() {
        val mongoDBDataSource = MongoDBDataSource(MongoConfiguration(database = "gxz", password = "123456"))
        mongoDBDataSource.testConnection()
        mongoDBDataSource.getTables()
    }
}
