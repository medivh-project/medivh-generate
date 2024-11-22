package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.env.TemplateContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLConfiguration(
    var url: String,
    var user: String,
    var password: String,
    var driver: String = "com.mysql.cj.jdbc.Driver"
) {

    fun createWriteRule(templateContext: TemplateContext): MySQLWriteRule {
        return MySQLWriteRule()
    }
}
