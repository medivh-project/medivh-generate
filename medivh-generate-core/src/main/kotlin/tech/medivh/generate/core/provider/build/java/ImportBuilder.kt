package tech.medivh.generate.core.provider.build.java

interface ImportBuilder {

    fun importClass(import: String): ImportBuilder

    fun importClass(clazz: Class<*>): ImportBuilder
}