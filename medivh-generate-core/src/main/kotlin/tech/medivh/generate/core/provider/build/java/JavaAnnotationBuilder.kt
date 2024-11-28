package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.BuilderComponent

/**
 * Builder interface for creating Java annotations.
 * Provides functionality to build Java annotations with various parameter types and styles.
 *
 * Example usages:
 * ```java
 * // Simple annotation: @Override
 * builder.name("Override")
 *
 * // Annotation with single value: @SuppressWarnings("unchecked")
 * builder.name("SuppressWarnings").value("unchecked")
 *
 * // Annotation with named parameters: @RequestMapping(path = "/users", method = RequestMethod.GET)
 * builder.name("RequestMapping")
 *       .param("path", "\"/users\"") //  Equivalent to .paramString("path", "users")
 *       .param("method", "RequestMethod.GET")
 * ```
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 */
interface JavaAnnotationBuilder : BuilderComponent {

    /**
     * Sets the name of the annotation.
     *
     * @param name The annotation name without the @ symbol
     * @return The current builder instance for method chaining
     */
    fun name(name: String): JavaAnnotationBuilder

    /**
     * Sets a single value for the annotation.
     * Equivalent to @AnnotationName(value)
     *
     * @param value The value to set
     * @return The current builder instance for method chaining
     */
    fun value(value: String): JavaAnnotationBuilder

    /**
     * Adds a named parameter to the annotation.
     * Treat value as a variable,that is to say don't add double quotes
     * Example: @AnnotationName(paramName = paramValue)
     *
     * @param name The parameter name
     * @param value The parameter value
     * @return The current builder instance for method chaining
     */
    fun param(name: String, value: String): JavaAnnotationBuilder

    /**
     * Adds a named parameter to the annotation.
     * Treat value as a string,that is to say will add double quotes
     * Example: @AnnotationName(paramName = "paramValue")
     *
     * @param name The parameter name
     * @param value The parameter value
     * @return The current builder instance for method chaining
     */
    fun stringParam(name: String, value: String): JavaAnnotationBuilder

    /**
     * Adds an array parameter to the annotation.
     * Example: we will get @AnnotationName(values = { 1, 2 })
     * ```java
     * builder.name("AnnotationName").arrayParam("values", listOf("1", "2"));
     * ```
     * @param name The array parameter name
     * @param values The array values
     * @return The current builder instance for method chaining
     */
    fun arrayParam(name: String, values: List<String>): JavaAnnotationBuilder

    /**
     * Adds an array parameter to the annotation.
     * Example: we will get @AnnotationName(values = { "tom", "cat" })
     * ```java
     * builder.name("AnnotationName").arrayParamString("values", listOf("tom", "cat"));
     * // Equivalent to
     * builder.name("AnnotationName").arrayParam("values", listOf("\"tom\"", "\"cat\""));
     * ```
     * @param name The array parameter name
     * @param values The array values
     * @return The current builder instance for method chaining
     */
    fun arrayParamString(name: String, values: List<String>): JavaAnnotationBuilder

    /**
     * Adds a class parameter to the annotation.
     *
     * Example: we will get @AnnotationName(type = String.class)
     * ```java
     * builder.name("AnnotationName").classParam("type", "String");
     * // Equivalent to
     * builder.name("AnnotationName").param("type", "String.class");
     * ```
     *
     *
     * @param name The parameter name
     * @param className The fully qualified class name
     * @return The current builder instance for method chaining
     */
    fun classParam(name: String, className: String): JavaAnnotationBuilder


    /**
     * Adds another annotation as a parameter.
     * Example: @OuterAnnotation(value = @InnerAnnotation)
     *
     * @param name The parameter name
     * @param annotationBuilder The nested annotation builder
     * @return The current builder instance for method chaining
     */
    fun nestedAnnotation(name: String, annotationBuilder: JavaAnnotationBuilder.() -> Unit): JavaAnnotationBuilder

}
