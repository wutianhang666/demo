package com.icss.etc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig {
      //跨域测试，不好用
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("") // 允许所有路径的跨域请求
//                .allowedOrigins("*") // 允许所有来源的跨域请求
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
//                .allowedHeaders("*"); // 允许的所有请求头
//    }
      @Bean
      public CorsWebFilter corsWebFilter(){
          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          CorsConfiguration corsConfiguration = new CorsConfiguration();
          corsConfiguration.setAllowedHeaders(Arrays.asList(new String("*")));
          corsConfiguration.setAllowedMethods(Arrays.asList(new String("*")));
          corsConfiguration.addAllowedOrigin("*");
          corsConfiguration.setAllowCredentials(true);

          source.registerCorsConfiguration("/**",corsConfiguration);
          return new CorsWebFilter(source);
      }
}
