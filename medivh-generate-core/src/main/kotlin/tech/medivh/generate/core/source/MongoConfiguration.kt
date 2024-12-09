package tech.medivh.generate.core.source

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MongoConfiguration(
    var host: String = "localhost",
    var port: Int = 27017,
    var user: String = "admin",
    var database: String,
    var password: String,
)

