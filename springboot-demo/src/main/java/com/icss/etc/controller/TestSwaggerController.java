package com.icss.etc.controller;

import com.icss.etc.Pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(value = "测试Swagger2在线接口文档")
public class TestSwaggerController {

    @GetMapping()
    @ApiOperation(value = "根据用户唯一标识获取用户信息")
    public User getUserInfo() {
        // 模拟数据库中根据id获取User信息
        User user = new User("111", "张三", "123456", "男","15998000000");
        return user;
    }
}
