package tech.medivh.generate.core.event

import tech.medivh.generate.core.Template
import tech.medivh.generate.core.env.GeneratorContext
import java.io.File

/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface MedivhEvent


/**
 *
 * target file is exist, and will be covered
 * @param text text will be written
 *
 **/
class BeforeCoverEvent(val targetFile: File, val text: String) : MedivhEvent

/**
 * target file is exist and will be skipped
 */
class TargetFileExistEvent(val targetFile: File) : MedivhEvent

/**
 * template provider skip this template
 */
class SkipTemplate(val template: Template, val context: GeneratorContext) : MedivhEvent

class WriteEvent(file: File) : MedivhEvent



