package com.icss.etc.controller;

import com.icss.etc.service.AopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "aop测试", description = "aop测试")
@RequestMapping("/aop")
public class TestAopController {

    @Autowired
    private AopService aopService;

    @GetMapping("/testAop")
    public void testAop(){
        aopService.testAop();
    }

    @GetMapping("/testAop2")
    public void testAop2(){
        System.out.println("我是主程序2");
    }

}
