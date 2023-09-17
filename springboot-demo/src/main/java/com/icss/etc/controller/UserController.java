package com.icss.etc.controller;

import com.icss.etc.pojo.User;
import com.icss.etc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "用户管理")
@RequestMapping("/user")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    @ApiOperation(value = "查询所有用户")
    public List<User> userList(){
        return userService.userList();
    }
    
    @PutMapping("/addUser")
    @ApiOperation(value = "新增用户")
    public int addUser(@RequestBody User user){
//        user.setId("1");
//        user.setUserName("张三");
//        user.setPassWord("123456");
//        user.setSex("男");
//        user.setPhone("5757262");
        
        return userService.addUser(user);
    }
}
