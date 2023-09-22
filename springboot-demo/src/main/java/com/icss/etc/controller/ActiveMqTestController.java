package com.icss.etc.controller;

import com.icss.etc.service.MqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

@RestController
@Api(value = "activeMQ消息队列测试", description = "activeMQ消息队列测试")
@RequestMapping("/mq")
public class ActiveMqTestController {

    private static Logger logger = LoggerFactory.getLogger(ActiveMqTestController.class);

    @Autowired
    private MqMessageService mqMessageService;

    public static final String MQ_BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "xiaoTian";

    @GetMapping("/sendMessage")
    @ApiOperation(value = "消息队列手动发送消息")
    public void sendMessage(@ApiParam("消息内容") @RequestParam String message) {
        System.out.println("---发送消息：" + message);
        mqMessageService.sendMessage(message);
    }

    @GetMapping("/doMessage")
    @ApiOperation(value = "消息队列手动接收消息")
    public void doMessage() {
        mqMessageService.doMessage();
    }

    @GetMapping("/JMSProduceQueueSendMessage")
    @ApiOperation(value = "queue--模拟生产者往Queue发送消息")
    public void JMSProduceQueueSendMessage() throws JMSException {
        //1先通过ActiveMQConnectionFactory 获得mq工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);

        //2获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3通过connection 获得Session
        //第一个参数叫事务，默认用false
        //第二个参数叫签收，默认自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4通过session创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);

        //5通过session 创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        System.out.println("发送的内容----------");
        for (int i = 0; i < 3; i++) {
            //6编写发送的消息(提问卡msg)
            TextMessage textMessage = session.createTextMessage("提问" + i);
            //7 messageProducer开始发送消息到MQ
            producer.send(textMessage);

            System.out.println("提问" + i);
        }

        //8释放资源
        connection.close();
        session.close();
        producer.close();
        System.out.println("发送完毕");
    }

    /**
     * queue队列--模拟消费者接收消息（同步阻塞方式版本）
     * 启动本方法，消费者可自动接收提供者发送的消息
     */
    @Test
    public void JMSProduceMessage() throws JMSException {
        //1先通过ActiveMQConnectionFactory 获得mq工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);

        //2 获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3通过connection 获得Session
        //第一个参数叫事务，默认用false
        //第二个参数叫签收，默认自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4通过session创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);

        //5通过session 创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);

         /*
            同步阻塞方式receive() ，订阅者或接收者调用MessageConsumer的receive()方法来接收消息，
            receive()将一直阻塞
            receive(long timeout) 按照给定的时间阻塞，到时间自动退出
        */

        System.out.println("接收到的消息-------------");
        while (true) {
            //发什么格式的消息，接收什么格式的消息
//            TextMessage textMessage = (TextMessage)consumer.receive(); //不见不散
            TextMessage textMessage = (TextMessage) consumer.receive(60 * 1000); //过时不侯

            //如果使用不见不散的方式时，消费消息之后程序不会结束，还是会继续等待，如果生产者在次发送消息的话，消费者会直接消费。
            //如果使用过时不候的方式时，消费消息之后程序会等待到约定的时间结束运行，在约定的时间内生产者在次发送消息的话，消费者会直接消费。
            //http://127.0.0.1:8161/admin/queues.jsp  在客户端也页面查看Number Of Consumers 消费者连接情况可验证

            if (textMessage == null) {
                break;
            }
            System.out.println(textMessage.getText());
        }

        //8释放资源
        connection.close();
        session.close();
        consumer.close();

        System.out.println("接收完毕");
    }

    /**
     * queue队列--模拟消费者接收消息（异步非阻塞方式版本）
     * 启动本方法，消费者可自动接收提供者发送的消息
     */
    @Test
    public void JMSProduceSyncMessage() throws JMSException {
        //1先通过ActiveMQConnectionFactory 获得mq工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);

        //2 获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3通过connection 获得Session
        //第一个参数叫事务，默认用false
        //第二个参数叫签收，默认自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4通过session创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        //5通过session 创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);

        CountDownLatch countDownLatch = new CountDownLatch(3);

        //当有新的消息到达时，就会触发该监听器的onMessage方法
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // TODO Auto-generated method stub
                System.out.println("监听器----" + message);

            }
        });

         /*
            异步非阻塞方式(监听器onMessage())
            订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器，
            当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法。
         */

        consumer.setMessageListener(message -> {
            System.out.println("--------------------" + message);
            if(message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        //不释放了，因为监听器需要监听，所以要阻塞主线程
        System.out.println("接收完毕");
    }
}
