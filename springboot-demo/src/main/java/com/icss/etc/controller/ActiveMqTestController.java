package com.icss.etc.controller;

import com.icss.etc.service.MqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@RestController
@Api(value = "消息队列测试")
@RequestMapping("/mq")
public class ActiveMqTestController {

    private static Logger logger = LoggerFactory.getLogger(ActiveMqTestController.class);

    @Autowired
    private MqMessageService mqMessageService;

//    public static final String MQ_BROKER_URL = "tcp://10.10.10.120:61616";
//    public static final String QUEUE_NAME = "xiaoTian";

    @GetMapping("/sendMessage")
    @ApiOperation(value = "消息队列手动发送消息")
    public void sendMessage(@ApiParam("消息内容") @RequestParam String message){
        System.out.println("---发送消息：" + message);
        mqMessageService.sendMessage(message);
    }

    @GetMapping("/doMessage")
    @ApiOperation(value = "消息队列手动接收消息")
    public void doMessage(){
        mqMessageService.doMessage();
    }

    @GetMapping("/queueSendMessage")
    @ApiOperation(value = "模拟Queue发送消息")
    public void queueSendMessage(){
//        //1先通过ActiveMQConnectionFactory 获得mq工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);
//
//        //2 获得连接connection
//        Connection connection = activeMQConnectionFactory.createConnection();
//        connection.start();
//
//        //3通过connection 获得Session
//        //3.1第一个参数叫事务，默认用false
//        //3.2第二个参数叫签收，默认自动签收
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        //4通过session创建目的地
//        Queue queue = session.createQueue(QUEUE_NAME);
//
//        //5通过session 创建消息生产者
//        MessageProducer producer = session.createProducer(queue);
//
//        for(int i = 0; i<3; i++) {
//            //6编写发送的消息(提问卡msg)
//            TextMessage textMessage = session.createTextMessage("提问"+i);
//            //7 messageProducer开始发送消息到MQ
//            producer.send(textMessage);
//        }
//
//        //8释放资源
//        connection.close();
//        session.close();
//        producer.close();

        System.out.println("发送完毕");
    }
}
