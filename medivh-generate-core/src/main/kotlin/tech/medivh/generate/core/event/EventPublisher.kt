package tech.medivh.generate.core.event


/**
 *
 * event bus
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class EventPublisher {

    val listeners = mutableMapOf<Class<out MedivhEvent>, MutableList<EventListener<out MedivhEvent>>>()


    inline fun <reified T : MedivhEvent> addListener(listener: EventListener<T>) {
        listeners.computeIfAbsent(T::class.java) {
            mutableListOf()
        }.add(listener)
    }

    fun <T : MedivhEvent> publishEvent(event: T) {
        listeners[event.javaClass]?.forEach { it.onEvent(event) }
    }


}
