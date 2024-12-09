package tech.medivh.generate.core.source

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClients
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoDatabase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tech.medivh.generate.core.provider.db.Table

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MongoDBDataSource(configuration: MongoConfiguration) : DataSourceFacade {

    private var client: MongoClient

    private var database: MongoDatabase

    init {
        val credential = MongoCredential.createCredential(
            configuration.user,
            "admin",
            configuration.password.toCharArray()
        )
        val serverAddress = ServerAddress(configuration.host, configuration.port)

        val settings = MongoClientSettings.builder()
            .applyToClusterSettings { it.hosts(listOf(serverAddress)) }
            .credential(credential)
            .build()
        client = MongoClient(MongoClients.create(settings))
        database = client.getDatabase(configuration.database)
    }


    override fun testConnection() {
        log.info("test connection $client")
    }

    override fun getTables(): List<Table> {
        database.listCollectionNames().forEach { log.info(it) }
        return emptyList()
    }


    companion object {
        var log: Logger = LoggerFactory.getLogger(MySQLDataSource::class.java)
    }
}

