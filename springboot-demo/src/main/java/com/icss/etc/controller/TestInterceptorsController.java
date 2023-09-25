package com.icss.etc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试拦截器", description = "拦截器测试")
@RequestMapping("/testInterceptors")
public class TestInterceptorsController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/test")
    @ApiOperation("测试拦截器")
    public String test(){
        logger.info("测试拦截器方法输出");
        return "test";
    }
}
