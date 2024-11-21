package tech.medivh.generate.core.event

import kotlin.reflect.KClass
import kotlin.reflect.full.*


/**
 *
 * event bus
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class EventPublisher {

    val listeners = mutableMapOf<KClass<out MedivhEvent>, MutableList<EventListener<MedivhEvent>>>()


    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : MedivhEvent> addListener(listener: EventListener<T>) {
        listeners.computeIfAbsent(T::class) {
            mutableListOf()
        }.add(listener as EventListener<MedivhEvent>)
    }

    @Suppress("UNCHECKED_CAST")
    fun addListener(listener: Any) {
        listener::class.declaredFunctions
            .filter { it.hasAnnotation<Listen>() }
            .forEach { method ->
                val parameters = method.valueParameters
                check(parameters.size == 1 && parameters[0].type.isSubtypeOf(MedivhEvent::class.createType())) {
                    "add Listen annotation must be has one argument and the argument is the event type"
                }
                listeners.computeIfAbsent(parameters[0].type.classifier as KClass<out MedivhEvent>) {
                    mutableListOf()
                }.add { event ->
                    method.call(listener, event)
                }
            }
    }

    fun <T : MedivhEvent> publishEvent(event: T) {
        listeners[event::class]?.forEach { it.onEvent(event) }
    }


}
