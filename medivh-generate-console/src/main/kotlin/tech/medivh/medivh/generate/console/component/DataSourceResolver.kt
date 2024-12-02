package tech.medivh.medivh.generate.console.component

import tech.medivh.generate.core.source.SourceType
import tech.medivh.medivh.generate.console.core.SourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface DataSourceResolver {

    fun support(): SourceType

    fun resolve(requestJson: Map<String, Any>): SourceFacade

}
