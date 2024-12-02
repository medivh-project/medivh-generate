package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.env.BaseGenerateContext
import tech.medivh.generate.core.event.Bus
import tech.medivh.generate.core.event.EventPublisher

/**
 * Java Builder
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaBuilderGeneratorContext(
    publisher: Bus = EventPublisher(),
    var className: String? = null,
    var packageName: String? = null,
    var imports: MutableSet<String> = mutableSetOf(),
    var fields: MutableList<String> = mutableListOf(),
    var annotations: MutableList<String> = mutableListOf()
) : BaseGenerateContext(publisher) {

    /**
     * 添加导入
     */
    fun addImport(import: String) {
        imports.add(import)
    }

    /**
     * 添加字段
     */
    fun addField(field: String) {
        fields.add(field)
    }

    /**
     * 添加注解
     */
    fun addAnnotation(annotation: String) {
        annotations.add(annotation)
    }

    /**
     * 清空上下文
     */
    fun clear() {
        className = null
        packageName = null
        imports.clear()
        fields.clear()
        annotations.clear()
    }
}
