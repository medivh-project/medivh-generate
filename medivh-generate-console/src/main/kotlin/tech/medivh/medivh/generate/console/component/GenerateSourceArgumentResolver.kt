package tech.medivh.medivh.generate.console.component

/**
 * @author gongxuanzhangmelt@gmail.com
 */
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import tech.medivh.medivh.generate.console.annotation.GenerateSource
import tech.medivh.medivh.generate.console.core.SourceFacade

class GenerateSourceArgumentResolver(private val sourceManager: SourceManager) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(GenerateSource::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): SourceFacade {
        return sourceManager.getSource()
    }

}
