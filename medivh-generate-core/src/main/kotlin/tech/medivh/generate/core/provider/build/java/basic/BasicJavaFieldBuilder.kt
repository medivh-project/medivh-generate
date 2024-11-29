package tech.medivh.generate.core.provider.build.java.basic

import tech.medivh.generate.core.provider.build.*
import tech.medivh.generate.core.provider.build.java.ImportBuilder
import tech.medivh.generate.core.provider.build.java.JavaAnnotationBuilder
import tech.medivh.generate.core.provider.build.java.JavaCommentBuilder
import tech.medivh.generate.core.provider.build.java.JavaFieldBuilder
import java.lang.reflect.Modifier

/**
 * @author gongxuanzhangmelt@gmail.com
 */
class BasicJavaFieldBuilder(private val parent: BuilderComponent) :
    JavaFieldBuilder<JavaCommentBuilder, JavaAnnotationBuilder>, ImportBuilder by parent {

    private var modifiers = Modifier.PRIVATE
    private var fieldName: String? = null
    private var fieldType: String? = null
    private var commentBuilder: JavaCommentBuilder? = null
    private val annotations = linkedSetOf<JavaAnnotationBuilder>()

    override fun name(name: String) = apply {
        this.fieldName = name
    }

    override fun name(nameSupplier: () -> String) = apply {
        this.fieldName = nameSupplier()
    }

    override fun type(type: String) = apply {
        this.fieldType = type
    }

    override fun type(typeSupplier: () -> String) = type(typeSupplier())

    override fun type(typeClass: Class<*>) = type(typeClass.name)

    override fun comment(action: JavaCommentBuilder.() -> Unit) = apply {
        commentBuilder = BasicJavaCommentBuilder(this).also {
            it.action()
            it.checkMySelf()
        }
    }

    override fun annotation(action: JavaAnnotationBuilder.() -> Unit) = apply {
        val annotationBuilder = BasicJavaAnnotationBuilder(this)
        annotationBuilder.action()
        annotationBuilder.checkMySelf()
        check(annotations.add(annotationBuilder)) { "$annotationBuilder already exists" }
    }

    override fun privateField() = apply { this.modifiers = modifiers.setPrivate() }
    override fun publicField() = apply { this.modifiers = modifiers.setPublic() }
    override fun protectedField() = apply { this.modifiers = modifiers.setProtected() }
    override fun defaultField() = apply { this.modifiers = modifiers.setDefault() }

    override fun staticField() = apply { this.modifiers = modifiers.or(Modifier.STATIC) }
    override fun finalField() = apply { this.modifiers = modifiers.or(Modifier.FINAL) }
    override fun volatileField() = apply { this.modifiers = modifiers.or(Modifier.VOLATILE) }
    override fun synchronizedField() = apply { this.modifiers = modifiers.or(Modifier.SYNCHRONIZED) }
    override fun transientField() = apply { this.modifiers = modifiers.or(Modifier.TRANSIENT) }

    override fun parent(): BuilderComponent = parent

    override fun checkMySelf() {
        require(!fieldName.isNullOrBlank()) { "Field name must not be null or blank" }
        require(!fieldType.isNullOrBlank()) { "Field type must not be null or blank" }
    }

    override fun text(): String = buildString {
        commentBuilder?.let { appendLine(it.text()) }
        annotations.forEach { appendLine(it) }
        appendLine("${Modifier.toString(modifiers)} $fieldType $fieldName;")
    }

    override fun toString(): String = text()
}
