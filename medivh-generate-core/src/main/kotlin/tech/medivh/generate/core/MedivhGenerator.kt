package tech.medivh.generate.core

import tech.medivh.generate.core.env.GeneratorContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
object MedivhGenerator {


    fun generateCode(context: GeneratorContext) {
        context.templateProvider.getTemplates().forEach { template ->
            template.write()
        }
    }

}

