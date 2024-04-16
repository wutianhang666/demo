package com.icss.etc.service.impl;

import com.icss.etc.service.TestActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestActiveMqServiceImpl implements TestActiveMqService {

    @Autowired(required = false)
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMessage(String message) {
        jmsMessagingTemplate.convertAndSend("order.queue.message",message);
        System.out.println("---发送消息成功");
    }

    @Override
    public String doMessage() {
        String message = jmsMessagingTemplate.receiveAndConvert("order.queue.message",String.class);
        System.out.println("***已经接收到信息：" + message);
        return message;
    }
}
