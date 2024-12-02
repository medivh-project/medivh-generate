package tech.medivh.medivh.generate.console.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import tech.medivh.medivh.generate.console.component.GenerateSourceArgumentResolver
import tech.medivh.medivh.generate.console.component.SourceManager

@Configuration
class MvcConfig : WebMvcConfigurer {

    @Autowired
    lateinit var sourceManager: SourceManager

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(GenerateSourceArgumentResolver(sourceManager))
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}
