package tech.medivh.generate.plugin.mybatisplus

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import tech.medivh.generate.core.ContextProvider
import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.source.DataSourceFacade

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class MyBatisPlusContextProvider(
    private val config: MyBatisPlusGenerateConfig,
    private val sourceFacade: DataSourceFacade
) : ContextProvider {


    override fun computeContext(): List<GeneratorContext> {
        return sourceFacade.computeContext().map { it.putAll(JSON.toJSON(config) as JSONObject); it }
    }


}
