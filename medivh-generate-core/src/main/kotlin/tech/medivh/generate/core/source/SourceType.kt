package tech.medivh.generate.core.source

/**
 *
 * support data source
 * @author gongxuanzhangmelt@gmail.com
 */
enum class SourceType(val support: Boolean = true) {

    MYSQL,
    ORACLE(false),
    MONGODB(false),
    SQL_SERVER(false),
    POSTGRESQL(false),
    H2(false),
    JSON(false),

}
