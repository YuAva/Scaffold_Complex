package cn.edu.bupt.hover.scaffold.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    // interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    // cors
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
