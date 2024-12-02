package tech.medivh.medivh.generate.console.component

import org.springframework.stereotype.Component
import tech.medivh.medivh.generate.console.core.SourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Component
class SourceManager {

    private var sourceFacade: SourceFacade? = null

    /**
     * register source
     * @return source token
     */
    fun registerSource(sourceFacade: SourceFacade) {
        this.sourceFacade = sourceFacade
    }

    fun getSource(): SourceFacade {
        return sourceFacade ?: throw IllegalArgumentException("source not found")
    }
}
