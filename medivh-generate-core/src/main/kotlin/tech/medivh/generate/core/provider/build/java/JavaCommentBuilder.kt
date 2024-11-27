package tech.medivh.generate.core.provider.build.java


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
class JavaCommentBuilder(private val parent: JavaBuilderComponent) : JavaBuilderComponent, ImportBuilder by parent {

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

    fun author(author: String) = apply {
        tags.add("author" to author)
    }

    fun since(since: String) = apply {
        tags.add("since" to since)
    }

    fun date(date: String) = apply {
        tags.add("date" to date)
    }

    override fun checkMySelf() {
        if (type == CommentType.LINE && tags.isNotEmpty()) {
            throw IllegalStateException("line comment can not have tags")
        }
    }

    override fun parent() = parent


    override fun toString(): String {
        return commentLines.takeIf { it.isNotEmpty() }?.run {
            val builder = StringBuilder()
            when (type) {
                CommentType.BLOCK -> {
                    builder.appendLine("/**")
                    commentLines.forEach { builder.appendLine(" * $it") }
                    tags.forEach { builder.appendLine(" * @${it.first} ${it.second}") }
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


