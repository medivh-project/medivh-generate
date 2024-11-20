package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.filter

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.ALL_TEMPLATE
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.VmTemplate
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.Table


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class TargetTemplateFilter(targetTemplateName: String) : TemplateFilter {

    private val targetTemplateNameList = targetTemplateName.split(",")

    private val enabled = targetTemplateName != ALL_TEMPLATE

    override fun allow(table: Table, template: VmTemplate): Boolean {
        if (!enabled) {
            return true
        }
        return targetTemplateNameList.any { it.equals(template.name, true) }
    }
}