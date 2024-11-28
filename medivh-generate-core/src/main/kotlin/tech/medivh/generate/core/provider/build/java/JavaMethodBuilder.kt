package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.BuilderComponent

/**
 * Builder interface for creating Java methods.
 * Provides a fluent API for constructing Java method declarations with various modifiers,
 * parameters, exceptions, and method body.
 *
 * Example usages:
 * ```java
 * // Method with parameters:
 * builder.name("processUser")
 *        .publicMethod()
 *        .returnType("String")
 *        .parameter {
 *            type("Long")
 *            name("id")
 *            annotation {
 *                name("NotNull")
 *            }
 *        }
 * ```
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 */
interface JavaMethodBuilder<C, A, P> : BuilderComponent
        where C : JavaCommentBuilder, A : JavaAnnotationBuilder, P : JavaMethodParamBuilder<*> {

    /**
     * Sets the method name.
     */
    fun name(name: String): JavaMethodBuilder<C, A, P>

    /**
     * Sets the return type of the method.
     */
    fun returnType(type: String): JavaMethodBuilder<C, A, P>

    /**
     * Sets the method return type to void.
     */
    fun returnVoid(): JavaMethodBuilder<C, A, P>

    /**
     * Adds a parameter to the method using the parameter builder.
     * Example:
     * ```kotlin
     * param {
     *     type("String")
     *     name("username")
     *     annotation {
     *         name("NotNull")
     *     }
     * }
     * ```
     */
    fun param(paramBuilder: P.() -> Unit): JavaMethodBuilder<C, A, P>

    /**
     * Adds an exception to the list of thrown exceptions for the method.
     *
     * @param exception The fully qualified name of the exception to be added.
     * @return The [JavaMethodBuilder] instance for method chaining.
     */
    fun throwsException(exception: String): JavaMethodBuilder<C, A, P>


    /**
     * Adds an exception to the list of thrown exceptions for the method based on the provided exception class.
     *
     * @param eClass The class object of the exception to be added to the method's thrown exceptions.
     * @return The [JavaMethodBuilder] instance for method chaining.
     */
    fun throwsException(eClass: Class<in Exception>): JavaMethodBuilder<C, A, P>

    /**
     * Sets the method body.
     */
    fun body(body: String): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method public.
     */
    fun publicMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method private.
     */
    fun privateMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method protected.
     */
    fun protectedMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method package-private.
     */
    fun defaultMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method static.
     */
    fun staticMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method final.
     */
    fun finalMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method abstract.
     */
    fun abstractMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method synchronized.
     */
    fun synchronizedMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Makes the method native.
     */
    fun nativeMethod(): JavaMethodBuilder<C, A, P>

    /**
     * Adds a comment to the method.
     */
    fun comment(commentBuilder: C.() -> Unit): JavaMethodBuilder<C, A, P>

    /**
     * Adds an annotation to the method.
     */
    fun annotation(annotationBuilder: A.() -> Unit): JavaMethodBuilder<C, A, P>
}
