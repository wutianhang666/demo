package com.icss.etc.service.impl;

import com.icss.etc.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqMessageServiceImpl implements MqMessageService {

    @Autowired(required = false)
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(String message) {
        System.out.println("等待发送的信息为：" + message);
        jmsMessagingTemplate.convertAndSend("order.queue.message",message);
    }

    @Override
    public String doMessage() {
        String message = jmsMessagingTemplate.receiveAndConvert("order.queue.message",String.class);
        System.out.println("已经接收到信息：" + message);
        return message;
    }
}
