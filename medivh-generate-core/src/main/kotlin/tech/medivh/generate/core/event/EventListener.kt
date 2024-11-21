package tech.medivh.generate.core.event


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface EventListener<E : MedivhEvent> {

    fun onEvent(event: E)

}
