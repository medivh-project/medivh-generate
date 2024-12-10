package tech.medivh.generate.core.source

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Accumulators.addToSet
import com.mongodb.client.model.Accumulators.sum
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Sorts
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.litote.kmongo.*
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
        return database.listCollectionNames().map { name ->
            val collection = MongoCollection(name, database.name, "WiredTiger", null)
            collection.columns.addAll(scanMongoCollectionDetail(name))
            collection.adaptToTable()
        }.toList()
    }

    private fun scanMongoCollectionDetail(collectionName: String): List<MongoColumn> {
        val collection = database.getCollection<Document>(collectionName)
        val pipeline = listOf(
            sample(200),
            project(Projections.computed("fields", Document("\$objectToArray", ("\$\$ROOT")))),
            unwind("\$fields"),
            group(
                "\$fields.k",
                addToSet("types", Document("\$type", "\$fields.v")),
                sum("count", 1)
            ),
            sort(Sorts.descending("count"))
        )
        val result = collection.aggregate<MongoColumn>(pipeline)
        return result.toList()
    }


    companion object {
        var log: Logger = LoggerFactory.getLogger(MySQLDataSource::class.java)
    }
}

