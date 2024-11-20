package com.zhonghaiwenda.cryptolabs.gradle.plugin.generator

import org.apache.velocity.VelocityContext


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
enum class VmTemplate(
    val templateName: String,
    val subPackage: String,
    val fileNameAction: (VelocityContext) -> String,
    val skipIfRelationTable: Boolean = false
) {
    DOMAIN("Domain.java.vm", "domain", { "${it["className"]}.java" }),

    QUERY_DOMAIN("QueryDomain.java.vm", "domain.query", { "${it["className"]}Query.java" }, true),

    MAPPER("Mapper.java.vm", "mapper", { "${it["className"]}Mapper.java" }),

    SERVICE("Service.java.vm", "service", { "${it["className"]}Service.java" }, true),

    SERVICE_IMPL("ServiceImpl.java.vm", "service.impl", { "${it["className"]}ServiceImpl.java" }, true),

    CONTROLLER("Controller.java.vm", "controller", { "${it["className"]}Controller.java" }, true),

}
