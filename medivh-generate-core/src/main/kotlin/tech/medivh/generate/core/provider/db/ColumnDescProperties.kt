package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.NameStyle
import tech.medivh.generate.core.convert


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class ColumnDescProperties(ormColumnDesc: ColumnDesc) : ColumnDesc by ormColumnDesc {

    val fieldClass = TypeConvertor.convert(ormColumnDesc.name)

    val fieldName = fieldClass.simpleName

    val fieldname = fieldName.convert(NameStyle.CAMEL)
}
