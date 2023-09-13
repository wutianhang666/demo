package com.icss.etc.controller;

import com.icss.etc.Pojo.User;
import com.icss.etc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户管理")
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/addUser")
    @ApiOperation(value = "新增用户")
    public int addUser(User user){
        user.setId("1");
        user.setUserName("张三");
        user.setPassWord("123456");
        user.setSex("男");
        user.setPhone("5757262");
        
        return userService.addUser(user);
    }
}
