package com.abc.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new SomeInterceptor())
               // .addPathPatterns("/first/**");    // 拦截所有以/first开头的请求路径
                .excludePathPatterns("/first/**");  // 除了以/first开头的请求外，其它请求路径都拦截
    }
}
