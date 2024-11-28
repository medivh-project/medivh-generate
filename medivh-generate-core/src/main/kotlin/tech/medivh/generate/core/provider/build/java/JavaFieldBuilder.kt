package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.BuilderComponent


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface JavaFieldBuilder<C, A> : BuilderComponent where C : JavaCommentBuilder, A : JavaAnnotationBuilder {

    /**
     * Sets the name of the field direct.
     * @param name the name of the field
     * @return the current builder instance
     */
    fun name(name: String): JavaFieldBuilder<C, A>

    /**
     * Sets the name of the field by a supplier.
     * @param nameSupplier the name of the field
     * @return the current builder instance
     */
    fun name(nameSupplier: () -> String): JavaFieldBuilder<C, A>

    /**
     * Specifies the type of the field being built.
     * The input should be the fully qualified class name.
     *
     * @param type The fully qualified type of the field as a string.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun type(type: String): JavaFieldBuilder<C, A>

    /**
     * Specifies the type of the field being built using a supplier function.
     *
     * This method is an alternative way to set the field type, allowing for dynamic
     * type resolution at runtime through the provided supplier function.
     *
     * @param typeSupplier A function that supplies the fully qualified
     *                     type of the field as a `String`.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun type(typeSupplier: () -> String) = type(typeSupplier())

    /**
     * Specifies the type of the field being built using a class reference.
     *
     * This is a convenience method that internally uses the [type] method accepting a string,
     * converting the provided [Class] object's name into a string format and passing it along.
     * This method is recommended.
     * @param typeClass The class reference whose name will be used as the type of the field.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun type(typeClass: Class<*>) = type(typeClass.name)

    /**
     * Adds a comment to the field by configuring the provided comment builder.
     *
     * @param action Configuration block for the comment builder which specifies how the comment should be defined.
     * @return The current [JavaFieldBuilder] instance for fluent API chaining.
     */
    fun comment(action: C.() -> Unit): JavaFieldBuilder<C, A>

    /**
     * Adds an annotation to the field by configuring the provided annotation builder.
     *
     * @param action Configuration block for the annotation builder which specifies how the annotation should be defined.
     * @return The current [JavaFieldBuilder] instance for fluent API chaining.
     */
    fun annotation(action: A.() -> Unit): JavaFieldBuilder<C, A>

    /**
     * Makes the field private.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun privateField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field public.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun publicField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field protected.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun protectedField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field package-private.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun defaultField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field static.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun staticField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field final.
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun finalField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field volatile.
     *
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun volatileField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field synchronized.
     *
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun synchronizedField(): JavaFieldBuilder<C, A>

    /**
     * Makes the field transient.
     *
     * @return The current [JavaFieldBuilder] instance for method chaining.
     */
    fun transientField(): JavaFieldBuilder<C, A>


}
