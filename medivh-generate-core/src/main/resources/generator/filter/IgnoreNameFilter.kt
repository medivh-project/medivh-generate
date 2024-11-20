package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.filter

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.SelectTableDesc


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class IgnoreNameFilter(ignoreNames: String) : TableFilter {

    private val ignoreNameSet = ignoreNames.split(",").toSet()

    override fun allow(tableDesc: SelectTableDesc): Boolean {
        return tableDesc.tableName !in ignoreNameSet
    }
}