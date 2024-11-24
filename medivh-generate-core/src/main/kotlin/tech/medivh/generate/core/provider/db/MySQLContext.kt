package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.env.AbstractTemplateContext
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLTemplateContext(
    vmFileName: String,
    val table: Table,
    private val config: MySQLConfiguration
) : AbstractTemplateContext(vmFileName) {

    init {
        this.publisher.addListener(MySQLFillListener())
    }

    override fun overwrite(): Boolean {
        return config.overwrite
    }

    override fun targetFile(template: TemplateContext): File {
        return config.targetFileAction.invoke(template as MySQLTemplateContext)
    }


    override fun toString(): String {
        return "(table=${table.tableName}, vmFile=${vmFileName})"
    }

}
