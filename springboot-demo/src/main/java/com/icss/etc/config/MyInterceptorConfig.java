package com.icss.etc.config;

import com.icss.etc.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加我们创建的拦截器
        registry.addInterceptor(new MyInterceptor())
                //addPathPatterns配置要拦截哪些路径
                .addPathPatterns("/testInterceptors/**");
        super.addInterceptors(registry);
    }

    /**
     * 继承WebMvcConfigurationSupport 会导致默认的静态资源被拦截，这就需要我们手动将静态资源放开。
     * 除了在 MyInterceptorConfig 配置类中重写 addInterceptors 方法外，
     * 还需要再重写一个方法：addResourceHandlers，将静态资源放开
     * 用来指定静态资源不被拦截，否则继承WebMvcConfigurationSupport这种方式会导致静态资源无法直接访问
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("swagger-ui.html");
        super.addResourceHandlers(registry);
    }
}

/**
 * 还有一种简便方法
 * 我们不继承 WebMvcConfigurationSupport 类，直接实现 WebMvcConfigurer 接口，
 * 然后重写 addInterceptors 方法，将自定义的拦截器添加进去即可，如下：
 */
//@Configuration
//public class MyInterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 实现WebMvcConfigurer不会导致静态资源被拦截
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//    }
//}

