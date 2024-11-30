package tech.medivh.medivh.generate.console.component

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.method.support.HandlerMethodArgumentResolver

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(GenerateSourceArgumentResolver(MappingJackson2HttpMessageConverter()))
    }
}
