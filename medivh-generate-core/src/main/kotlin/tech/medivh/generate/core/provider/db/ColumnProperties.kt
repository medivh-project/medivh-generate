package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.NameStyle
import tech.medivh.generate.core.convert


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class ColumnProperties(column: Column) : Column by column {

    val fieldClass = TypeConvertor.convert(column.name)

    val fieldName = fieldClass.simpleName

    val fieldname = fieldName.convert(NameStyle.CAMEL)
}
