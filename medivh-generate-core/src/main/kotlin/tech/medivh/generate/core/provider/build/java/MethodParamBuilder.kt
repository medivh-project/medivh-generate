package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class MethodParamBuilder(private val methodBuilder: JavaMethodBuilder) {

    fun done(): JavaMethodBuilder {
        return methodBuilder
    }

    fun nextParam(): MethodParamBuilder {
        return methodBuilder.parameters()
    }
}
