package tech.medivh.generate.plugin.mybatisplus

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class ControllerBuilder {

    private var restController = true

    private var requestMapping = ""

    private var controllerClassName = ""

    private var serviceField = ""
    private var entityClass = ""
    private var generateCrud = true
    private var superClassName = ""

    fun restController(rest: Boolean) = apply {
        this.restController = rest
    }

    fun requestMapping(path: String) = apply {
        this.requestMapping = path
    }

    fun className(className: String) = apply {
        this.controllerClassName = className.takeUnless { it.endsWith("Controller") }?.plus("Controller") ?: className
    }

    fun withService(serviceFieldName: String) = apply {
        this.serviceField = serviceFieldName
    }

    fun withEntity(entityClassName: String) = apply {
        this.entityClass = entityClassName
    }

    fun generateCrud(generate: Boolean) = apply {
        this.generateCrud = generate
    }

    fun superClass(superClass: String) = apply {
        this.superClassName = superClass
    }

    fun build(): String {
        val sb = StringBuilder()

        // 添加类注解
        if (restController) {
            sb.append("@RestController\n")
        } else {
            sb.append("@Controller\n")
        }

        if (requestMapping.isNotEmpty()) {
            sb.append("@RequestMapping(\"$requestMapping\")\n")
        }

        // 添加类定义
        sb.append("class $controllerClassName")
        if (superClassName.isNotEmpty()) {
            sb.append(" : $superClassName")
        }
        sb.append(" {\n")

        // 添加服务字段
        if (serviceField.isNotEmpty()) {
            sb.append("    @Autowired\n")
            sb.append("    private lateinit var $serviceField: ${serviceField.capitalize()}Service\n\n")
        }

        // 生成CRUD方法
        if (generateCrud && entityClass.isNotEmpty()) {
            generateCrudMethods(sb)
        }

        sb.append("}")

        return sb.toString()
    }

    private fun generateCrudMethods(sb: StringBuilder) {
        // 添加查询方法
        sb.append("""
            @GetMapping("/{id}")
            fun getById(@PathVariable id: Long): $entityClass {
                return ${serviceField}.getById(id)
            }
            
            @PostMapping
            fun save(@RequestBody entity: $entityClass): Boolean {
                return ${serviceField}.save(entity)
            }
            
            @PutMapping
            fun update(@RequestBody entity: $entityClass): Boolean {
                return ${serviceField}.updateById(entity)
            }
            
            @DeleteMapping("/{id}")
            fun deleteById(@PathVariable id: Long): Boolean {
                return ${serviceField}.removeById(id)
            }
        """.trimIndent())
    }
}
