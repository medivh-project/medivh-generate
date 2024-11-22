package tech.medivh.generate.core.provider.db


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface TableFilter {

    fun allow(table: Table): Boolean
}
