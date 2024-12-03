package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.NameStyle
import tech.medivh.generate.core.convert


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class ColumnProperties(column: Column) {


    val comment = column.comment

    val notNull = column.notNull

    val isPk = column.pk

    /**
     *  varchar -> java.lang.String
     */
    val fieldClass = TypeConvertor.convert(column.dataType)

    /**
     * sysUser
     */
    val fieldType = fieldClass.simpleName.convert(NameStyle.CAMEL)

    /**
     * SysUser
     */
    val fieldtype = fieldClass.simpleName.convert(NameStyle.HUMP)

    /**
     * user_name -> UserName
     */
    val fieldName = column.name.convert(NameStyle.HUMP)

    /**
     * user_name -> userName
     */
    val fieldname = column.name.convert(NameStyle.CAMEL)
}
