package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.GeneratorConfig
import org.apache.ibatis.datasource.pooled.PooledDataSource
import org.apache.ibatis.io.Resources
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.TransactionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.yaml.snakeyaml.Yaml


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/

val generatorConfig: GeneratorConfig = loadGeneratorConfig()

val sqlSessionFactory: SqlSessionFactory = createSqlSessionFactory()

const val TEMPLATE_DIR = "template"

const val ALL_TABLES = "all"

const val ALL_TEMPLATE = "all"

private fun loadGeneratorConfig(): GeneratorConfig {
    val yaml = Yaml()
    return yaml.loadAs(Resources.getResourceAsReader("generator.yml"), GeneratorConfig::class.java)
}


private fun createSqlSessionFactory(): SqlSessionFactory {
    val dataSource = PooledDataSource()
    dataSource.driver = "com.mysql.cj.jdbc.Driver" // 数据库驱动
    dataSource.url = generatorConfig.dataSource.url
    dataSource.username = generatorConfig.dataSource.username
    dataSource.password = generatorConfig.dataSource.password

    val transactionFactory: TransactionFactory = JdbcTransactionFactory()
    val environment = Environment("development", transactionFactory, dataSource)
    val configuration = Configuration(environment)
    configuration.addMapper(TableMapper::class.java)
    return SqlSessionFactoryBuilder().build(configuration)
}

inline fun <reified M> SqlSessionFactory.mapperDo(closure: (M) -> Unit) {
    this.openSession().use {
        closure(it.getMapper(M::class.java))
    }
}

fun camelToSnake(input: String): String {
    return input.fold(StringBuilder()) { acc, char ->
        when {
            char.isUpperCase() -> {
                if (acc.isNotEmpty()) acc.append('_')
                acc.append(char.lowercaseChar())
            }

            else -> acc.append(char)
        }
    }.toString()
}

fun snakeToCamel(columnName: String): String {
    val parts = columnName.split('_')
    val camelCase = parts.mapIndexed { index, s ->
        if (index == 0) {
            s.lowercase()
        } else {
            s.substring(0, 1).uppercase() + s.substring(1).lowercase()
        }
    }.joinToString("")
    return camelCase
}
