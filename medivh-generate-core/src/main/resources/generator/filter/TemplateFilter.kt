package generator.filter

import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.VmTemplate
import com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean.Table


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun interface TemplateFilter {

    fun allow(table: Table, template: VmTemplate): Boolean
}
