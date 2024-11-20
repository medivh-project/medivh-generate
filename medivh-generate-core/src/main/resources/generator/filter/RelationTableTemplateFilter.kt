package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.filter

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.VmTemplate
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.Table


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class RelationTableTemplateFilter : TemplateFilter {


    override fun allow(table: Table, template: VmTemplate): Boolean {
        val allow = !(table.relationTable && template.skipIfRelationTable)
        if (!allow) {
            println("skip [${template.templateName}] from relation table [${table.tableName}]")
        }
        return allow
    }
}