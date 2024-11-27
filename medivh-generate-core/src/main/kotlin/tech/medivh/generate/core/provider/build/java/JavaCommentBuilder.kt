package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
abstract class JavaCommentBuilder<out P> {

    private var type: CommentType = CommentType.BLOCK

    private val commentLines = mutableListOf<String>()

    private val tags = mutableListOf<Pair<String, String>>()

    fun blockComment() = apply {
        this.type = CommentType.BLOCK
    }

    fun lineComment() = apply {
        this.type = CommentType.LINE
    }

    fun text(text: String) = apply {
        commentLines.addAll(text.split("\n"))
    }

    fun tag(key: String, line: String) = apply {
        tags.add(key to line)
    }

    abstract fun build(): P

    override fun toString(): String {
        return commentLines.takeIf { it.isNotEmpty() }?.run {
            when (type) {
                CommentType.BLOCK -> {
                    StringBuilder().apply {
                        appendLine("/**")
                        this.forEach { appendLine(" * $it") }
                        appendLine(" **/")
                    }.toString()
                }

                CommentType.LINE -> {
                    StringBuilder().apply {
                        this.forEach { appendLine(" // $it") }
                        appendLine(" **/")
                    }.toString()
                }
            }
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
}
