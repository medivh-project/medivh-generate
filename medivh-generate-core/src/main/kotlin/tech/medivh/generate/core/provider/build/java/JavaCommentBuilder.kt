package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
abstract class JavaCommentBuilder<out P> {

    private var type: CommentType = CommentType.BLOCK

    private val commentLines = mutableListOf<String>()

    private val tags = mutableListOf<Pair<String, String>>()

    open fun blockComment() = apply {
        this.type = CommentType.BLOCK
    }

    open fun lineComment() = apply {
        this.type = CommentType.LINE
    }

    open fun text(text: String) = apply {
        commentLines.addAll(text.split("\n"))
    }

    open fun tag(key: String, line: String) = apply {
        tags.add(key to line)
    }

    abstract fun build(): P

    override fun toString(): String {
        return commentLines.takeIf { it.isNotEmpty() }?.run {
            val builder = StringBuilder()
            when (type) {
                CommentType.BLOCK -> {
                    builder.appendLine("/**")
                    commentLines.forEach { builder.appendLine(" * $it") }
                    builder.appendLine(" **/")
                }

                CommentType.LINE -> {
                    commentLines.forEach { builder.appendLine(" // $it") }
                }
            }
            builder.toString()
        } ?: ""
    }

    enum class CommentType {
        BLOCK, LINE
    }
}


class MethodCommentBuilder(private val parent: JavaMethodBuilder) : JavaCommentBuilder<JavaMethodBuilder>() {
    override fun build() = parent
}

class FieldCommentBuilder(private val parent: JavaFieldBuilder) : JavaCommentBuilder<JavaFieldBuilder>() {
    override fun build() = parent
}

class ClassCommentBuilder(private val parent: JavaBuilder) : JavaCommentBuilder<JavaBuilder>() {
    override fun build() = parent

    fun author(author: String) = apply {
        tag("author", author)
    }

    override fun blockComment() = apply {
        super.blockComment()
    }

    override fun lineComment() = apply {
        super.lineComment()
    }

    override fun text(text: String) = apply {
        super.text(text)
    }

    override fun tag(key: String, line: String) = apply {
        super.tag(key, line)
    }

}
