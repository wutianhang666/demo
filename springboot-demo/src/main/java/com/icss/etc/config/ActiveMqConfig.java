package com.icss.etc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;


/**
 * activeMQ的配置
 */
@Configuration
public class ActiveMqConfig {

    /**
     * 监听器自动接收消息
     *
     * @param message
     * @JmsListener是Spring框架提供的注解，用于监听消息队列中的消息。destination属性指定了要监听的消息队列的名称，
     * 表示监听名为"order.queue.message"的消息队列。当消息队列中有新的消息时，
     * 使用@JmsListener注解的方法会被自动调用来处理这些消息。
     *
     * @SendTo("other.queue") 该注解将消息继续发送给其他队列
     */
    @JmsListener(destination = "order.queue.message")
    @SendTo("other.queue")
    private String receive(String message) {
        System.out.println("***监听器自动接收order.queue.message队列消息：" + message);
        return message;
    }

    @JmsListener(destination = "other.queue")
    private void receiveOther(String message) {
        System.out.println("接收到other.queue继续发送的消息：" + message);
    }
}
