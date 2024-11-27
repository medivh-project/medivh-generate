package tech.medivh.generate.core.provider.build.java

/**
 *
 *
 * @author gongxuanzhangmelt@gmail.com
 */
interface JavaBuilderComponent : ImportBuilder {

    /**
     * builder should check something after configuration
     */
    fun checkMySelf()

    /**
     * parent builder
     */
    fun parent(): JavaBuilderComponent


}