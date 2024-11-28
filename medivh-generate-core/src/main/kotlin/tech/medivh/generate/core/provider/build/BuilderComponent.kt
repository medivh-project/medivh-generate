package tech.medivh.generate.core.provider.build

import tech.medivh.generate.core.provider.build.java.ImportBuilder

/**
 *
 *
 * @author gongxuanzhangmelt@gmail.com
 */
interface BuilderComponent : ImportBuilder {

    /**
     * builder should check something after configuration
     * throw exception if illegal
     */
    fun checkMySelf()

    /**
     * parent builder
     */
    fun parent(): BuilderComponent

    /**
     * generate text (more than code)
     */
    fun text(): String


}
