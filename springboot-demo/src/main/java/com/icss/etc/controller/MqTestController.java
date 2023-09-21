package com.icss.etc.controller;

import com.icss.etc.service.MqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "消息队列测试")
@RequestMapping("/mq")
public class MqTestController {

    private static Logger logger = LoggerFactory.getLogger(MqTestController.class);

    @Autowired
    private MqMessageService mqMessageService;

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
}
