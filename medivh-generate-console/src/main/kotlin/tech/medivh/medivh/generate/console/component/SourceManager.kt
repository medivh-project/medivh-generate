package tech.medivh.medivh.generate.console.component

import org.springframework.stereotype.Component
import tech.medivh.generate.core.source.DataSourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Component
class SourceManager {

    private var sourceFacade: DataSourceFacade? = null

    /**
     * register source
     * @return source token
     */
    fun registerSource(sourceFacade: DataSourceFacade) {
        this.sourceFacade = sourceFacade
    }

    fun getSource(): DataSourceFacade {
        return sourceFacade ?: throw IllegalArgumentException("source not found")
    }
}
