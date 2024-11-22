package tech.medivh.generate.core.event


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface Bus {

    fun <T : MedivhEvent> addListener(listener: EventListener<T>)

    fun addListener(listener: Any)

    fun <T : MedivhEvent> publishEvent(event: T)
}
