package com.icss.etc.config;

import com.icss.etc.interceptor.CorsInterceptor;
import com.icss.etc.interceptor.LoginCheckInterceptor;
import com.icss.etc.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //测试登录拦截
        registry.addInterceptor(new MyInterceptor())
                //addPathPatterns配置要拦截哪些路径
                .addPathPatterns("/testInterceptors/**");

        //拦截登录请求
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("")
                .excludePathPatterns("/sys/login");

        //拦截器添加跨域处理
        registry.addInterceptor(new CorsInterceptor())
                .addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    /**
     * 继承WebMvcConfigurationSupport 会导致默认的静态资源被拦截，这就需要我们手动将静态资源放开。
     * 除了在 InterceptorConfig 配置类中重写 addInterceptors 方法外，
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
 * 还有一种简便方法----实测好用
 * 我们不继承 WebMvcConfigurationSupport 类，直接实现 WebMvcConfigurer 接口，
 * 然后重写 addInterceptors 方法，将自定义的拦截器添加进去即可，如下：
 */
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 实现WebMvcConfigurer不会导致静态资源被拦截
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//    }
//}

