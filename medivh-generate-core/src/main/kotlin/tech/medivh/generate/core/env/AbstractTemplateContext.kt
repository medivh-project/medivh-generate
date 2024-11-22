package tech.medivh.generate.core.env

import tech.medivh.generate.core.event.EventPublisher
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
abstract class AbstractTemplateContext(override val vmFile: File) : TemplateContext {

    protected val context = mutableMapOf<String, Any>()

    override val bus: EventPublisher = EventPublisher()

    override fun put(key: String, value: Any): Any {
        return context.put(key, value)!!
    }

    override fun get(key: String): Any? {
        return context[key]
    }

    override fun containsKey(key: String): Boolean {
        return context.containsKey(key)
    }

    override fun getKeys(): Array<String> {
        return context.keys.toTypedArray()
    }

    override fun remove(key: String): Any? {
        return context.remove(key)
    }
}
