package tech.medivh.medivh.generate.console.component

import tech.medivh.generate.core.source.DataSourceFacade
import tech.medivh.generate.core.source.SourceType


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface DataSourceResolver {

    fun support(): SourceType

    fun resolve(requestJson: Map<String, Any>): DataSourceFacade

}
