package tech.medivh.generate.core

import tech.medivh.generate.core.env.GeneratorContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface ContextExtension {

    /**
     * you can extend the context here.
     * also return a new context if you want
     */
    fun doExtend(context: GeneratorContext): GeneratorContext
}

val EnlightenedContextExtension = ContextExtension { it }
