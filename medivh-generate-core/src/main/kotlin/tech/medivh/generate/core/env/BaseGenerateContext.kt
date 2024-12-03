package tech.medivh.generate.core.env

import tech.medivh.generate.core.event.Bus
import tech.medivh.generate.core.event.EventPublisher
import tech.medivh.generate.core.provider.db.Table


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
open class BaseGenerateContext(
    private val table: Table,
    private val publisher: Bus = EventPublisher()
) : GeneratorContext, Bus by publisher {

    private val vmContext = mutableMapOf<String, Any>()


    override fun put(key: String, value: Any): Any? {
        return vmContext.put(key, value)
    }

    override fun get(key: String): Any? {
        return vmContext[key]
    }

    override fun containsKey(key: String): Boolean {
        return vmContext.containsKey(key)
    }

    override fun getKeys(): Array<String> {
        return vmContext.keys.toTypedArray()
    }

    override fun remove(key: String): Any? {
        return vmContext.remove(key)
    }

    override fun putAll(properties: Map<String, Any>) {
        return vmContext.putAll(properties)
    }

    override fun table(): Table {
        return table
    }
}
