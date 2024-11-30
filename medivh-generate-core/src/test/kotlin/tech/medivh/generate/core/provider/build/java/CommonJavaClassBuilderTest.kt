package tech.medivh.generate.core.provider.build.java

import org.junit.jupiter.api.Test
import tech.medivh.generate.core.provider.build.java.basic.BasicJavaClassBuilder
import tech.medivh.generate.core.provider.build.java.basic.BasicJavaMethodBuilder


class CommonJavaClassBuilderTest {
    @Test
    fun testClassGenerate() {
        val myClass = BasicJavaClassBuilder().name("MyClassName")
            .publicClass()
            .finalClass()
            .method(buildMethod1)
            .method {
                name("myMethod2")
                annotation { name("MyAnnotation2") }
            }
            .field {
                name("myField1")
            }.build()
    }

    private val buildMethod1: (BasicJavaMethodBuilder) -> Unit = {
        it.name("myMethod1").annotation { name("MyAnnotation1") }
    }


}
