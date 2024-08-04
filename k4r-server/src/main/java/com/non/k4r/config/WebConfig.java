package com.non.k4r.config;

import com.non.k4r.inteceptor.AuthorizationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义的拦截器
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**") // 指定拦截所有路径
                .excludePathPatterns("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**"); // 排除静态资源
    }
}
