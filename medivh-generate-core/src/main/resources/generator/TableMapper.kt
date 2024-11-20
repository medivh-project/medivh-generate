package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.SelectColumnDesc
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.SelectTableDesc
import org.apache.ibatis.annotations.Select

/**
 * @author gongxuanzhangmelt@gmail.com
 */
interface TableMapper {


    @Select(
        """select table_name tableName, engine, table_comment tableComment, create_time createTime
        from information_schema.tables
        where table_schema = (select database())
        order by create_time desc
                 """
    )
    fun queryTableInfos(): List<SelectTableDesc>


    @Select(
        """select table_name tableName, engine, table_comment tableComment, create_time createTime from 
                information_schema.tables
                where table_schema = (select database()) and table_name = #{tableName}
                """
    )
    fun queryTable(tableName: String): List<SelectTableDesc>

    @Select(
        """select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey,
                extra from information_schema.columns
                where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position"""
    )
    fun queryColumns(tableName: String): List<SelectColumnDesc>
}
