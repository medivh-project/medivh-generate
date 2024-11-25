package tech.medivh.generate.core.provider.db

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object TypeConvertor {

    private val typeMapping: Map<String, Class<*>> = mapOf(
        "tinyint" to Integer::class.java,
        "smallint" to Integer::class.java,
        "mediumint" to Integer::class.java,
        "int" to Integer::class.java,
        "integer" to Integer::class.java,
        "bigint" to Long::class.java,
        "float" to Float::class.java,
        "double" to Double::class.java,
        "decimal" to BigDecimal::class.java,
        "bit" to Boolean::class.java,
        "char" to String::class.java,
        "varchar" to String::class.java,
        "tinytext" to String::class.java,
        "text" to String::class.java,
        "mediumtext" to String::class.java,
        "longtext" to String::class.java,
        "date" to LocalDateTime::class.java,
        "datetime" to LocalDateTime::class.java,
        "timestamp" to Instant::class.java,
        "NUMBER" to Integer::class.java,
        "INT" to Integer::class.java,
        "INTEGER" to Integer::class.java,
        "BINARY_INTEGER" to Integer::class.java,
        "LONG" to String::class.java,
        "FLOAT" to Float::class.java,
        "BINARY_FLOAT" to Float::class.java,
        "DOUBLE" to Double::class.java,
        "BINARY_DOUBLE" to Double::class.java,
        "DECIMAL" to BigDecimal::class.java,
        "CHAR" to String::class.java,
        "VARCHAR" to String::class.java,
        "VARCHAR2" to String::class.java,
        "NVARCHAR" to String::class.java,
        "NVARCHAR2" to String::class.java,
        "CLOB" to String::class.java,
        "BLOB" to String::class.java,
        "DATE" to LocalDate::class.java,
        "DATETIME" to LocalDate::class.java,
        "TIMESTAMP" to Instant::class.java,
        "TIMESTAMP(6)" to Instant::class.java,
        "int8" to Long::class.java,
        "int4" to Integer::class.java,
        "int2" to Integer::class.java,
        "numeric" to BigDecimal::class.java,
        "nvarchar" to String::class.java
    )


    fun convert(columnType: String): Class<*> {
        return typeMapping[columnType] ?: throw IllegalArgumentException("unsupported type $columnType")
    }
}
