package tech.medivh.generate.core.provider.db

import org.apache.velocity.app.Velocity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.ktorm.database.Database
import tech.medivh.generate.core.engine.MedivhGenerator
import java.io.StringWriter


class JdbcTemplateProviderTest {

    @BeforeEach
    fun init() {
        MedivhGenerator
    }

    @Test
    fun testEvaluate() {
        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/seal_bridge",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "123456"
        )
        val context = JDBCContextProvider(database).computeContext()
        val s = StringWriter()
        Velocity.evaluate(context.first(), s, "test", "\$className ")
        println(s.toString())
    }

}
