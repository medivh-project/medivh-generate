package tech.medivh.generate.core

import tech.medivh.generate.core.env.GeneratorContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface ContextProvider {

    fun computeContext(): List<GeneratorContext>
}
