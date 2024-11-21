package tech.medivh.generate.core.event


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface EventListener<in E : MedivhEvent> {

    fun onEvent(event: E)

}
