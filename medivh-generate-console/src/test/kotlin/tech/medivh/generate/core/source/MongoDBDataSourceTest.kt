package tech.medivh.generate.core.source

import org.junit.jupiter.api.Test


class MongoDBDataSourceTest {

    @Test
    fun testTestConnection() {
        val mongoDBDataSource = MongoDBDataSource(MongoConfiguration(database = "test_db", password = "admin123"))
        mongoDBDataSource.testConnection()
        println(mongoDBDataSource.getTables())
    }
}
