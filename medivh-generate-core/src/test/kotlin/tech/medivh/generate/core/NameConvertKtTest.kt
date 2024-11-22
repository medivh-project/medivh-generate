package tech.medivh.generate.core

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class NameConvertKtTest {
    @Test
    fun testConvert() {
        val testCases = listOf(
            "hello_world",
            "Hello_World",
            "helloWorld",
            "HelloWorld",
        )

        for (case in testCases) {
            assertEquals("helloWorld", case.convert(NameStyle.CAMEL))
            assertEquals("hello_world", case.convert(NameStyle.UNDERLINE))
            assertEquals("HelloWorld", case.convert(NameStyle.HUMP))
        }
    }
}
