package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.BuilderComponent

/**
 * Builder interface for Java method parameter.
 * Supports method parameter definition with type, name, annotations and modifiers.
 *
 * Example usages:
 * ```java
 * // Simple parameter: Long id
 * paramBuilder.type("Long").name("id")
 *
 * // Annotated parameter: @NotNull @Size(max = 50) String name
 * paramBuilder.type("String")
 *             .name("name")
 *             .annotation { name("NotNull") }
 *             .annotation {
 *                 name("Size")
 *                 param("max", "50")
 *             }
 *
 * // Final parameter with generic type: final List<String> items
 * paramBuilder.type("List<String>")
 *             .name("items")
 *             .finalParam()
 * ```
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 */
interface JavaMethodParamBuilder<A : JavaAnnotationBuilder> : BuilderComponent {

    /**
     * Sets parameter type.
     * Supports both simple and generic types.
     */
    fun type(type: String): JavaMethodParamBuilder<A>

    /**
     * Sets parameter name.
     */
    fun name(name: String): JavaMethodParamBuilder<A>

    /**
     * Marks parameter as final.
     */
    fun finalParam(): JavaMethodParamBuilder<A>

    /**
     * Adds annotation to parameter.
     */
    fun annotation(builder: A.() -> Unit): JavaMethodParamBuilder<A>

    /**
     * Marks parameter as varargs.
     * Example: String... args
     */
    fun varargs(): JavaMethodParamBuilder<A>

}
