package tech.medivh.generate.plugin.mybatisplus

import org.junit.jupiter.api.Test
import tech.medivh.generate.core.source.MySQLDataSource
import tech.medivh.generate.core.source.MySqlConfiguration


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class TestPlugin {

    @Test
    fun testPlugin() {
        val plugin = MybatisPlusPlugin()
        val config = MySqlConfiguration(database = "seal_bridge", password = "123456")
        plugin.setDataSource(MySQLDataSource(config))
        plugin.config = MyBatisPlusGenerateConfig(mainPath = "tech.medivh")
        plugin.generateAll()
    }
}
