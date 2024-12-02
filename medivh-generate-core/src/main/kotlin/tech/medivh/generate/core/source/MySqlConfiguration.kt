package tech.medivh.generate.core.source

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MySqlConfiguration(
    var host: String = "localhost",
    var port: Int = 3306,
    var user: String = "root",
    var database: String,
    var password: String,
    var driver: String = "com.mysql.cj.jdbc.Driver"
)
