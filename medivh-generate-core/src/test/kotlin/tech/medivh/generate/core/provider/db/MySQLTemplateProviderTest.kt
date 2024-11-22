package tech.medivh.generate.core.provider.db

import org.junit.jupiter.api.Test
import java.io.File


class MySQLTemplateProviderTest {


    @Test
    fun testSelectTable() {
        val config = MySQLConfiguration(
            "jdbc:mysql://localhost:3306/seal_bridge",
            "root",
            "123456",
            targetFileAction = { _ -> File("a") })
        val templates = MySQLTemplateProvider(config).getTemplates()
        println(templates)
    }

    @Test
    fun testHelloWorld() {
        val config = MySQLConfiguration(
            "jdbc:mysql://localhost:3306/seal_bridge",
            "root",
            "123456",
            targetFileAction = { context -> File("${context.table.tableName}${context.vmFileName}") })
        val templates = MySQLTemplateProvider(config).getTemplates()
        templates.forEach { it.write() }
    }
}
