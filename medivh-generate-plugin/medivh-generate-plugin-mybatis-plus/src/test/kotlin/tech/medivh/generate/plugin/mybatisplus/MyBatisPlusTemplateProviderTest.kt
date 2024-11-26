package tech.medivh.generate.plugin.mybatisplus

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class MyBatisPlusTemplateProviderTest {
    @Test
    fun testProvider() {
        val provider = MyBatisPlusTemplateProvider()
        val templates = provider.getTemplates()
        assertEquals(8, templates.size)
    }
}
