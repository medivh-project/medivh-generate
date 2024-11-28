package tech.medivh.generate.core.provider.build.java

import tech.medivh.generate.core.provider.build.BuilderComponent

/**
 * Builder interface for creating Java comments.
 * Provides functionality to build different types of Java comments including multi-line comments,
 * single-line comments, and Javadoc comments.
 *
 * @author gxz gongxuanzhangmelt@gmail.com
 */
interface JavaCommentBuilder : BuilderComponent {

    /**
     * Sets the comment type to multi-line comment.
     * @return The current builder instance for method chaining
     */
    fun multilineComment(): JavaCommentBuilder

    /**
     * Sets the comment type to single-line comment.
     * @return The current builder instance for method chaining
     */
    fun singleLineComment(): JavaCommentBuilder

    /**
     * Sets the comment type to Javadoc comment.
     * Doc comment can have tags.
     * @return The current builder instance for method chaining
     */
    fun docComment(): JavaCommentBuilder

    /**
     * Adds a documentation tag to the comment.
     * This method is typically used with docComment() to add Javadoc tags.
     * Common tags include @param, @return, @throws, @see, @since, etc.
     *
     * Example usage:
     * ```java
     * builder.docComment()
     *        .tag("param", "name The user's name")
     *        .tag("return", "The processed result");
     * ```
     *
     * @param key The tag name without the @ symbol (e.g., "param", "return")
     * @param line The content/description for the tag
     * @return The current builder instance for method chaining
     * @throws IllegalStateException if called on a non-Javadoc comment
     */
    fun tag(key: String, line: String): JavaCommentBuilder


}
