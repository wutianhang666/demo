package com.icss.etc.service.impl;

import com.icss.etc.service.TestAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestAsyncServiceImpl implements TestAsyncService {

    /**
     * @Async注解的方法，称之为异步方法，该方法会在执行时，会单独开启一个线程来执行
     * 去springboot主程序中开启异步注解功能  @EnableAsync
     */

    @Override
    @Async
    public void testAsync() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在传输...");
    }
}
