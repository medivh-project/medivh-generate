package tech.medivh.generate.core.provider.db

import tech.medivh.generate.core.NameStyle
import tech.medivh.generate.core.convert


/**
 * Table related attributes are all level 1 attributes
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class TableProperties(table: Table) {

    /**
     * hello_world -> HelloWorld
     */
    var className = table.tableName.convert(NameStyle.HUMP)

    /**
     * hello_world -> helloWorld
     */
    var classname = className.convert(NameStyle.CAMEL)

    /**
     *  table comment
     */
    var comment = table.comment

    /**
     * primaryKey, if table has multiple primary keys, only the first one is taken.
     * if there is no primary key, the first column is taken as the primary key.
     * because multiple primary key may be a junction table.
     * pk is not a list is better to use in templates.
     */
    var pk: Column = table.columns.firstOrNull { it.pk } ?: table.columns.first()

    /**
     * Non-primary key column
     */
    var columns = table.columns.filter { !it.pk }

    /**
     * this table is a relation table
     */
    var junctionTable = false

}
