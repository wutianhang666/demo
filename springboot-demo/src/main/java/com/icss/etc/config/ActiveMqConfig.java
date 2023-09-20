package com.icss.etc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;


/**
 * activeMQ的配置
 */
@Configuration
public class ActiveMqConfig {

    @JmsListener(destination = "order.queue.message")
    private void receive(String message){
        System.out.println("自动接收到消息："+ message);
    }

}
