package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.env.AbstractTemplateContext
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MySQLTemplateContext(
    vmFile: File,
    val table: Table,
    private val config: MySQLConfiguration
) : AbstractTemplateContext(vmFile) {

    override fun overwrite(): Boolean {
        return config.overwrite
    }

    override fun targetFile(template: TemplateContext): File {
        return config.targetFileAction.invoke(template)
    }


    override fun toString(): String {
        return "(table=${table.tableName}, vmFile=${vmFile.name})"
    }

}
