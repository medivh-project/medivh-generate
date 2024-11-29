package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.env.GeneratorContext
import tech.medivh.generate.core.provider.build.BuilderComponent

/**
 * Builder interface for creating Java classes.
 * Provides a fluent API for constructing Java class declarations with various modifiers,
 * fields, methods, and inner classes.
 *
 * Example usage:
 * ```java
 * builder.name("UserService")
 *        .publicClass()
 *        .superInterface("UserInterface")
 *        .superClass("BaseService")
 *        .annotation {
 *            name("Service")
 *        }
 *        .field {
 *            name("userRepository")
 *            type("UserRepository")
 *            privateField()
 *            annotation {
 *                name("Autowired")
 *            }
 *        }
 *        .method {
 *            name("findUser")
 *            publicMethod()
 *            returnType("User")
 *            parameter {
 *                name("id")
 *                type("Long")
 *            }
 *        }
 * ```
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface JavaClassBuilder<C, A, F, M> : BuilderComponent
        where C : JavaCommentBuilder,
              A : JavaAnnotationBuilder,
              F : JavaFieldBuilder<*, *>,
              M : JavaMethodBuilder<*, *, *> {

    /**
     * Sets the class name
     */
    fun name(name: String): JavaClassBuilder<C, A, F, M>

    /**
     * Sets the package name for the class
     */
    fun packageName(name: String): JavaClassBuilder<C, A, F, M>

    /**
     * Sets the superclass that this class extends
     */
    fun superClass(className: String): JavaClassBuilder<C, A, F, M>

    /**
     * Adds an interface that this class implements
     */
    fun superInterface(interfaceName: String): JavaClassBuilder<C, A, F, M>


    /**
     * Makes the class public
     */
    fun publicClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class private
     */
    fun privateClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class protected
     */
    fun protectedClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class package-private (default access)
     */
    fun defaultClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class static
     */
    fun staticClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class final
     */
    fun finalClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Makes the class abstract
     */
    fun abstractClass(): JavaClassBuilder<C, A, F, M>

    /**
     * Adds a field to the class
     */
    fun field(fieldBuilder: F.() -> Unit): JavaClassBuilder<C, A, F, M>

    /**
     * Adds a method to the class
     */
    fun method(methodBuilder: M.() -> Unit): JavaClassBuilder<C, A, F, M>

    /**
     * Adds an inner class to this class
     */
    fun innerClass(classBuilder: JavaClassBuilder<*, *, *, *>.() -> Unit): JavaClassBuilder<C, A, F, M>

    /**
     * Adds a comment to the class
     */
    fun comment(commentBuilder: C.() -> Unit): JavaClassBuilder<C, A, F, M>

    /**
     * Adds an annotation to the class
     */
    fun annotation(annotationBuilder: A.() -> Unit): JavaClassBuilder<C, A, F, M>

    /**
     * Constructs and returns the [GeneratorContext] based on the configured settings
     * of the [JavaClassBuilder].
     *
     * @return A [GeneratorContext] instance encapsulating the configuration for generating
     *         the Java class code.
     */
    fun build(): GeneratorContext

}
