package com.icss.etc.controller;

import com.icss.etc.service.MqMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "消息队列测试")
@RequestMapping("/mq")
public class MqTestController {

    @Autowired
    private MqMessageService mqMessageService;

    @GetMapping("/sendMessage")
    @ApiOperation(value = "发送消息")
    public void sendMessage(){
        String message = "陈老老老板";
        mqMessageService.sendMessage(message);
    }

    @GetMapping("/doMessage")
    @ApiOperation(value = "接收消息")
    public void doMessage(){
        mqMessageService.doMessage();
    }
}
