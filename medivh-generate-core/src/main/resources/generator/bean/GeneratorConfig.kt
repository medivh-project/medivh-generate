package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator.bean

import com.alibaba.fastjson2.JSONObject


/**
 * 生成器配置
 */
class GeneratorConfig {
    @Suppress("unused")
    lateinit var mainPath: String
    lateinit var author: String

    @Suppress("unused")
    lateinit var email: String
    lateinit var type: Map<String, String>
    lateinit var dataSource: DataSourceConfig
    lateinit var extension: Map<String, String>


    fun toMap(): Map<String, Any> {
        return JSONObject.from(this)
    }
}


class DataSourceConfig {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}
