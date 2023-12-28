package com.icss.etc.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 配置Aop切面
 */
@Component
@Aspect
public class AopProxyConfig {

    /**
     * @Pointcut 注解指定一个切面，定义需要拦截的东西，这里介绍两个常用的表达式：
     * 一个是使用 execution()，
     * 切入点，制定 com.icss.etc.controller.TestAopController 类下的所有方法
     * (..)表示任何参数都可以
     *
     *
     * 另一个是使用 annotation()。
     * annotation() 方式是针对某个注解来定义切面，比如我们对具有@GetMapping注解的方法做切面
     * @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
     */
    @Pointcut(value = "execution(* com.icss.etc.controller.TestAopController.*(..))")
    public void pointDemo() {
    }

    @Before(value = "pointDemo()")
    public void before() {
        System.out.println("before");
    }

    @After(value = "pointDemo()")
    public void after() {
        System.out.println("after");
    }


    /**
     * 1.@Pointcut：定义一个切面，即上面所描述的关注的某件事入口。
     * 2.@Before：在做某件事之前做的事。
     * 3.@After：在做某件事之后做的事。
     * 4.@AfterReturning：在做某件事之后，对其返回值做增强处理。
     * 5.@AfterThrowing：在做某件事抛出异常时，处理。
     *
     */
}
