package tech.medivh.medivh.generate.console.component

import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.util.StreamUtils
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import tech.medivh.medivh.generate.console.annotation.GenerateSource
import java.nio.charset.StandardCharsets

/**
 * @author gongxuanzhangmelt@gmail.com
 */
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

class GenerateSourceArgumentResolver(
    private val messageConverters: MappingJackson2HttpMessageConverter  // Spring 消息转换器
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(GenerateSource::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val generateSourceAnnotation = parameter.getParameterAnnotation(GenerateSource::class.java)

        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)

        val body =  ObjectMapper().readValue(request!!.inputStream , Map::class.java)

        return mapOf<String,String>()
    }


    private fun parseRequestBody(inputMessage: HttpInputMessage, parameter: MethodParameter): Map<String, Any> {
        return messageConverters.read(Map::class.java, inputMessage) as Map<String, Any>  ?: emptyMap()
    }

    private fun generateSourceCode(body: Map<String, Any>, value: String): String {
        return "Generated source based on: $value, Request body: $body"
    }
}
