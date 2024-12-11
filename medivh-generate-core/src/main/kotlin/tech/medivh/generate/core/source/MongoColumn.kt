package tech.medivh.generate.core.source

import org.bson.codecs.pojo.annotations.BsonId
import tech.medivh.generate.core.provider.db.Column


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
data class MongoColumn(
    @BsonId
    val id: String,
    val count: Int? = null,
    val types: List<String>? = null,
) {
    fun adaptToColum(collection: MongoCollection): Column {
        return Column(
            collection.tableName,
            id,
            false,
            id == "_id",
            types?.joinToString(",") ?: "String",
            collection.tableName
        )
    }
}
