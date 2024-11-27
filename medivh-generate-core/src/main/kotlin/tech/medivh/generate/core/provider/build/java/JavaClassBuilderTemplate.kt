package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.Template


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaClassBuilderTemplate : Template {

    override fun templateName(): String {
        return "builder-template"
    }

    override fun readText(): String {
        return "$$BUILDER_CONTEXT_KEY"
    }

    companion object {
        const val BUILDER_CONTEXT_KEY = "classText"
    }
}
