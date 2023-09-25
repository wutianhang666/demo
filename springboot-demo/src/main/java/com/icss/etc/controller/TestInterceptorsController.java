package com.icss.etc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试拦截器", description = "拦截器测试")
@RequestMapping("/testInterceptors")
public class TestInterceptorsController {

    @RequestMapping("/test")
    @ApiOperation("测试拦截器")
    public String test(){
        return "test";
    }
}
