package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean

/**
 * @author gongxuanzhangmelt@gmail.com
 */
data class Table(
    var tableName: String,
    var comments: String?,
    /**
     * 主键,如果是联合主键只会保存第一个。
     * 如果没有主键会保存第一列
     */
    var pk: Column,
    /**
     * 非主键列
     */
    var columns: List<Column>,
    /**
     * 类名(第一个字母大写)，如：sys_user => SysUser
     */
    var className: String,
    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     */
    var classname: String,
    /**
     * 是否是关联表
     */
    var relationTable: Boolean = false,

    var hasBigDecimal: Boolean = false,

    var hasLocalDateTime: Boolean = false,

    var hasInstant: Boolean = false

)