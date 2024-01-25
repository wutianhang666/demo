package com.icss.etc.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduledTaskController {

    //使用注解@Controller将此类声明为一个Bean
    //使用 @Scheduled 注解来指定定时任务的执行时间，方法里是执行逻辑
    //注意cron表达式的用法；
    //0/2 * * * * ?  每隔2秒执行一次
    @Scheduled(cron = "0/2 * * * * ?")
    public void myTask(){
        System.out.println("hello.....");
    }
}
