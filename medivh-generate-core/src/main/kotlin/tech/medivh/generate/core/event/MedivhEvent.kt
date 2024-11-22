package tech.medivh.generate.core.event

import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface MedivhEvent


abstract class TemplateEvent(val context: TemplateContext) : MedivhEvent

/**
 *
 * target file is exist, and will be covered
 * @param targetFile the file will be covered
 *
 **/
class BeforeCoverEvent(val targetFile: File, context: TemplateContext) : TemplateEvent(context)

/**
 * target file is exist and will be skipped
 */
class SkipFileEvent(val targetFile: File, context: TemplateContext) : TemplateEvent(context)

class NotAllowEvent(context: TemplateContext) : TemplateEvent(context)

class WriteEvent(context: TemplateContext) : TemplateEvent(context)

class BeforeMergeTemplateEvent(context: TemplateContext) : TemplateEvent(context)


