package tech.medivh.generate.core.provider.build

import java.lang.reflect.Modifier


/**
 * @author gxz gongxuanzhangmelt@gmail.com
 **/
fun Int.setPublic(): Int {
    return setDefault() or Modifier.PUBLIC
}

fun Int.setDefault(): Int {
    return this and Modifier.PUBLIC.inv() and Modifier.PRIVATE.inv() and Modifier.PROTECTED.inv()
}

fun Int.setProtected(): Int {
    return setDefault() or Modifier.PROTECTED
}

fun Int.setPrivate(): Int {
    return setDefault() or Modifier.PRIVATE
}
