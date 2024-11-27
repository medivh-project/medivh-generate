package tech.medivh.generate.core.provider.build.java

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JavaFieldBuilderTest {

    private lateinit var javaBuilder: JavaBuilder
    private lateinit var fieldBuilder: JavaFieldBuilder

    @BeforeEach
    fun setup() {
        javaBuilder = JavaBuilder()
        fieldBuilder = JavaFieldBuilder(javaBuilder)
    }

    @Test
    fun testBasicField() {
        fieldBuilder
            .name("username")
            .type(String::class.java)
            .build()

        assertEquals("private String username;", fieldBuilder.toString().trim())
    }

    @Test
    fun testPrivateField() {
        fieldBuilder
            .publicField()
            .privateField()
            .name("password")
            .type("java.lang.String")
            .build()

        assertEquals("private String password;", fieldBuilder.toString().trim())
    }

    @Test
    fun testPublicStaticFinalField() {
        fieldBuilder
            .publicField()
            .staticField()
            .finalField()
            .name("MAX_SIZE")
            .type("int")
            .build()

        assertEquals("public static final int MAX_SIZE;", fieldBuilder.toString().trim())
    }


    @Test
    fun testProtectedVolatileField() {
        fieldBuilder
            .protectedField()
            .volatileField()
            .name("instance")
            .type("Singleton")
            .build()

        assertEquals("protected volatile Singleton instance;", fieldBuilder.toString().trim())
    }

    @Test
    fun testAnnotatedField() {
        val field = fieldBuilder
            .privateField()
            .name("userId")
            .type("String")
            .annotation()
            .type("NotNull")
            .build()

        assertEquals("@NotNull\nprivate String userId;".trim(), fieldBuilder.toString().trim())
    }

    @Test
    fun testGenericField() {
        fieldBuilder
            .privateField()
            .name("items")
            .type("List<String>")
            .toString()

        assertEquals("private List<String> items;", fieldBuilder.toString().trim())
    }


}
