package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaClassBuilder

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class BasicJavaClassBuilder :
    JavaClassBuilder<BasicJavaCommentBuilder, BasicJavaAnnotationBuilder, BasicJavaFieldBuilder, BasicJavaMethodBuilder> {
    override fun name(name: String): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun packageName(name: String): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun superClass(className: String): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun superInterface(interfaceName: String): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun publicClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun privateClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun protectedClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun defaultClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun staticClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun finalClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun abstractClass(): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun build(): GeneratorContext {
        TODO("Not yet implemented")
    }

    override fun annotation(annotationBuilder: BasicJavaAnnotationBuilder.() -> Unit): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun comment(commentBuilder: BasicJavaCommentBuilder.() -> Unit): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun innerClass(classBuilder: JavaClassBuilder<*, *, *, *>.() -> Unit): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun method(methodBuilder: BasicJavaMethodBuilder.() -> Unit): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun field(fieldBuilder: BasicJavaFieldBuilder.() -> Unit): BasicJavaClassBuilder {
        TODO("Not yet implemented")
    }

    override fun checkMySelf() {
        TODO("Not yet implemented")
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
