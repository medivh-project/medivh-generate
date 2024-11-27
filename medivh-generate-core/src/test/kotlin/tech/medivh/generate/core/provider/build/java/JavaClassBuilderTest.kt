package tech.medivh.generate.core.provider.build.java

import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.find
import org.mockito.kotlin.mock
import tech.medivh.generate.core.engine.GenerateModule
import tech.medivh.generate.core.engine.MedivhGenerator
import tech.medivh.generate.core.engine.ModuleLoader
import tech.medivh.generate.core.provider.build.BuilderProvider


class JavaClassBuilderTest {
    @Test
    fun testController() {
        val javaClassBuilder = JavaClassBuilder()
        javaClassBuilder
            .publicClass()
            .className("medivh.generate.User")
            .comment {
                text("this is my test class\n this is my test class 2 ").author("gongxuanzhang")
            }
            .field {
                name("username").type(String::class.java)
            }
            .field {
                name("age").type("Integer")
                comment {
                    text("this is user's age")
                }
            }
            .method {
                name("getName").returnType("String").body("if(true){} if(false){} return this.userName;")
            }.method {
                name("setName").parameters {
                    name("name").type("String")
                }
            }.build()

        val provider = BuilderProvider(listOf(javaClassBuilder))
        val module = mock<GenerateModule>()
        `when`(module.contextProvider()).doReturn(provider)
        `when`(module.templateProvider()).doReturn(provider)
        ModuleLoader.installModule(module)
        MedivhGenerator.generate()
    }
}
