package tech.medivh.generate.core.event

import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaType


/**
 *
 * event bus
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class EventPublisher : Bus {

    private val listeners = mutableMapOf<Class<out MedivhEvent>, MutableList<EventListener<MedivhEvent>>>()


    @Suppress("UNCHECKED_CAST")
    override fun <T : MedivhEvent> addListener(listener: EventListener<T>) {
        val superType = listener::class.supertypes.find {
            it.classifier == EventListener::class
        } ?: throw IllegalArgumentException("listener must be a subclass of EventListener")

        val typeArgument = superType.arguments.firstOrNull()?.type
            ?: throw IllegalArgumentException("listener must be a subclass of EventListener")

        listeners.computeIfAbsent(typeArgument.javaType as Class<out MedivhEvent>) {
            mutableListOf()
        }.add(listener as EventListener<MedivhEvent>)
    }

    @Suppress("UNCHECKED_CAST")
    override fun addListener(listener: Any) {
        listener::class.declaredFunctions
            .filter { it.hasAnnotation<Listen>() }
            .forEach { method ->
                val parameters = method.valueParameters
                check(parameters.size == 1 && parameters[0].type.isSubtypeOf(MedivhEvent::class.createType())) {
                    "add Listen annotation must be has one argument and the argument is the event type"
                }
                listeners.computeIfAbsent(parameters[0].type.javaType as Class<out MedivhEvent>) {
                    mutableListOf()
                }.add { event ->
                    method.call(listener, event)
                }
            }
    }

    override fun <T : MedivhEvent> publishEvent(event: T) {
        listeners[event::class.java]?.forEach { it.onEvent(event) }
    }


}
