package tech.medivh.generate.core.event

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File


class EventPublisherTest {

    @Test
    fun testRegister() {
        val bus = EventPublisher()
        bus.addListener(TestCoverListener())
        bus.publishEvent(CoverEvent(File("a"), File("b")))
        assert(TestCoverListener.count == 1)
        bus.publishEvent(CoverEvent(File("a"), File("b")))
        assert(TestCoverListener.count == 2)
    }

    @Test
    fun testDynamic() {
        val bus = EventPublisher()
        bus.addListener(DynamicListener())
        bus.publishEvent(CoverEvent(File("a"), File("b")))
        assert(DynamicListener.count == 1)
        bus.publishEvent(CoverEvent(File("a"), File("b")))
        assert(DynamicListener.count == 2)
    }

    @Test
    fun errorDynamic() {
        val bus = EventPublisher()
        assertThrows<IllegalStateException> { bus.addListener(ErrorListener()) }
    }


    class ErrorListener {

        @Listen
        fun lis(event: CoverEvent, arg: Int) {
            println("error")
        }

    }

    class DynamicListener {

        @Listen
        fun lis(event: CoverEvent) {
            count++
        }

        companion object {
            var count = 0
        }
    }

    class TestCoverListener : EventListener<CoverEvent> {
        override fun onEvent(event: CoverEvent) {
            count++
        }

        companion object {
            var count = 0
        }
    }
}
