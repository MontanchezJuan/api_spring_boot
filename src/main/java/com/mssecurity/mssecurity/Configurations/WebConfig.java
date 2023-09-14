package com.mssecurity.mssecurity.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mssecurity.mssecurity.Interceptors.SecurityInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/public/**");
        // Asegúrate de que las rutas sean las correctas
    }
}
