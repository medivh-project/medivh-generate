package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaMethodBuilder

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class BasicJavaMethodBuilder :
    JavaMethodBuilder<BasicJavaCommentBuilder, BasicJavaAnnotationBuilder, BasicJavaMethodParamBuilder> {

    override fun name(name: String): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun returnType(type: String): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun returnVoid(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun throwsException(exception: String): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun throwsException(eClass: Class<in Exception>): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun body(body: String): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun publicMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun privateMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun protectedMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun defaultMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun staticMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun finalMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun abstractMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun synchronizedMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun nativeMethod(): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun annotation(annotationBuilder: BasicJavaAnnotationBuilder.() -> Unit): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun comment(commentBuilder: BasicJavaCommentBuilder.() -> Unit): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun param(paramBuilder: BasicJavaMethodParamBuilder.() -> Unit): BasicJavaMethodBuilder = apply {
        TODO("Not yet implemented")
    }

    override fun checkMySelf() {
        TODO()
    }

    override fun parent(): BuilderComponent {
        TODO("Not yet implemented")
    }

    override fun text(): String {
        TODO("Not yet implemented")
    }

    override fun importClass(import: String): ImportBuilder {
        TODO("Not yet implemented")
    }

    override fun importClass(clazz: Class<*>): ImportBuilder {
        TODO("Not yet implemented")
    }
}
