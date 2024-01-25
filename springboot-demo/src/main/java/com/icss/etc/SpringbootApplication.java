package com.icss.etc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.icss.etc.mapper")
@EnableAsync//开启异步任务功能
@EnableScheduling//开启基于注解的定时任务
public class SpringbootApplication {
    public static void main(String[] args) {
        System.out.println("开始启动");
        //SpringApplication.run(SysServiceBootApplication.class, args);
        ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("所有beanNames个数：" + beanNames.length);
        System.out.println("启动成功");
    }
}
