package com.icss.etc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
public class CorsConfig implements WebMvcConfigurer {
      //跨域测试，不好用
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("") // 允许所有路径的跨域请求
//                .allowedOrigins("*") // 允许所有来源的跨域请求
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
//                .allowedHeaders("*"); // 允许的所有请求头
//    }
}
