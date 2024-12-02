package tech.medivh.medivh.generate.console.component

import tech.medivh.medivh.generate.console.core.DataSource
import tech.medivh.medivh.generate.console.core.SourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface DataSourceResolver {

    fun support(): DataSource

    fun resolve(requestJson: Map<String, Any>): SourceFacade

}
