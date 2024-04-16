package com.icss.etc.controller;

import com.icss.etc.service.TestAsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "异步功能测试")
@RequestMapping("/async")
public class TestAsyncController {

    @Autowired
    private TestAsyncService asyncService;

    @GetMapping("/async")
    @ApiOperation(value = "测试异步执行")
    public String async(){
        asyncService.testAsync();

        System.out.println("开启异步执行");
        return "ok";
    }
}
