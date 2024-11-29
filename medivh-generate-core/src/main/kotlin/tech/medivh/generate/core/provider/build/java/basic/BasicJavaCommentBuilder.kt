package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.BuilderComponent
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaCommentBuilder

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class BasicJavaCommentBuilder(private val parent: BuilderComponent) : JavaCommentBuilder, ImportBuilder by parent {

    private var commentType: CommentType = CommentType.DOC

    private var tags: MutableMap<String, String> = linkedMapOf()

    private var context: String = ""

    override fun multilineComment(): BasicJavaCommentBuilder = apply {
        this.commentType = CommentType.MULTILINE
    }

    override fun singleLineComment(): BasicJavaCommentBuilder = apply {
        this.commentType = CommentType.SINGLE_LINE
    }

    override fun docComment(): BasicJavaCommentBuilder = apply {
        this.commentType = CommentType.DOC
    }

    override fun tag(key: String, line: String): BasicJavaCommentBuilder = apply {
        tags[key] = line
    }

    override fun context(context: String): BasicJavaCommentBuilder = apply {
        this.context = context
    }

    override fun checkMySelf() {
        check(!(commentType != CommentType.DOC && tags.isNotEmpty())) {
            "$commentType comment can't have tags"
        }
    }

    override fun parent(): BuilderComponent {
        return parent
    }


    override fun text(): String = buildString {
        appendLine(commentType.prefix)
        context.split("\n").joinToString { "${commentType.linePrefix} $it" }.let { appendLine(it) }
        tags.forEach { (key, value) ->
            appendLine(" * @$key $value")
        }
        appendLine(commentType.suffix)
    }


    override fun toString(): String {
        return text()
    }

    enum class CommentType(val prefix: String, val suffix: String, val linePrefix: String = "") {
        MULTILINE("/*", "*/", "*"),
        SINGLE_LINE("", "", "//"),
        DOC("/**", "*/", "*")
    }
}
