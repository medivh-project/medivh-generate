package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean

/**
 * 列的属性
 *
 */
data class Column(
    /**
     * 列名
     */
    var columnName: String?,

    /**
     * 列名类型
     */
    var dataType: String?,

    /**
     * 列名备注
     */
    var comments: String?,

    /**
     * 属性名称(第一个字母大写)，如：user_name => UserName
     */
    var attrName: String?,

    /**
     * 属性名称(第一个字母小写)，如：user_name => userName
     */
    var attrname: String?,

    /**
     * 属性类型
     */
    var attrType: String?,

    /**
     * 是否可以在sql中直接用eq来查询，String和基本类型的包装类会
     */
    var canEquals: Boolean?,

    /**
     * 是否是主键
     */
    var pk: Boolean = false,

    /**
     * auto_increment
     */
    var extra: String? = null
)
