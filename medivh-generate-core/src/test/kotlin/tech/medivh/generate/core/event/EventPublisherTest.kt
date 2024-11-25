package tech.medivh.generate.core.event

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import java.io.File


class EventPublisherTest {

    @Test
    fun testRegister() {
        val bus = EventPublisher()
        bus.addListener(TestCoverListener())
        bus.publishEvent(BeforeCoverEvent(File("a"), mock()))
        assert(TestCoverListener.count == 1)
        bus.publishEvent(BeforeCoverEvent(File("a"), mock()))
        assert(TestCoverListener.count == 2)
    }

    @Test
    fun testDynamic() {
        val bus = EventPublisher()
        bus.addListener(DynamicListener())
        bus.publishEvent(BeforeCoverEvent(File("a"), mock()))
        assert(DynamicListener.count == 1)
        bus.publishEvent(BeforeCoverEvent(File("a"), mock()))
        assert(DynamicListener.count == 2)
    }

    @Test
    fun errorDynamic() {
        val bus = EventPublisher()
        assertThrows<IllegalStateException> { bus.addListener(ErrorListener()) }
    }


    class ErrorListener {

        @Listen
        fun lis(event: BeforeCoverEvent, arg: Int) {
            println("error")
        }

    }

    class DynamicListener {

        @Listen
        fun lis(event: BeforeCoverEvent) {
            count++
        }

        companion object {
            var count = 0
        }
    }

    class TestCoverListener : EventListener<BeforeCoverEvent> {
        override fun onEvent(event: BeforeCoverEvent) {
            count++
        }

        companion object {
            var count = 0
        }
    }
}
