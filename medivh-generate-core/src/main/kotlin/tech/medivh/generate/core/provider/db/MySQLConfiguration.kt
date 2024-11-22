package tech.medivh.generate.core.provider.db

import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLConfiguration(
    var url: String,
    var user: String,
    var password: String,
    var overwrite: Boolean = false,
    var targetFileAction: (MySQLTemplateContext) -> File,
    var driver: String = "com.mysql.cj.jdbc.Driver"
)
