package tech.medivh.generate.core.event

import tech.medivh.generate.core.WriteRule
import tech.medivh.generate.core.env.TemplateContext
import java.io.File


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
interface MedivhEvent


/**
 *
 * target file is exist, and will be covered
 * @param coveredFile the file will be covered
 * @param backFile the file will be backup
 *
 **/
data class CoverEvent(val coveredFile: File, val backFile: File) : MedivhEvent

data class WriteRuleNotAllowEvent(val context: TemplateContext, val rule: WriteRule) : MedivhEvent
