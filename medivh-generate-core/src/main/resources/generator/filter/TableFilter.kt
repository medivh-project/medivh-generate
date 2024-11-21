package generator.filter

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.SelectTableDesc


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface TableFilter {

    fun allow(tableDesc: SelectTableDesc): Boolean
}
