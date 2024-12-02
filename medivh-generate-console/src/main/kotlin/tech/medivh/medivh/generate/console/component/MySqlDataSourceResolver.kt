package tech.medivh.medivh.generate.console.component

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tech.medivh.generate.core.source.SourceType
import tech.medivh.medivh.generate.console.core.MySqlSource
import tech.medivh.medivh.generate.console.core.SourceFacade


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
@Component
class MySqlDataSourceResolver : DataSourceResolver {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    override fun support(): SourceType {
        return SourceType.MYSQL
    }

    override fun resolve(requestJson: Map<String, Any>): SourceFacade {
        val config = objectMapper.convertValue<MySqlConfiguration>(requestJson)
        return MySqlSource(config)
    }
}
