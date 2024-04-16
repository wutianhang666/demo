package com.icss.etc.controller;

import com.icss.etc.annotation.Name;
import com.icss.etc.annotation.Sex;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.User;
import com.icss.etc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RestController
//@CrossOrigin
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/api/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "查询所有用户")
    public BaseResult userList() {
        logger.info("查询所有用户");
        List<User> list = userService.userList();
        logger.info("查询结果" + list.toString());
        return BaseResult.success(list);
    }

    @PutMapping("/addUser")
    @ApiOperation(value = "新增用户")
    public int addUser(@RequestBody User user) {
//        user.setId("1");
//        user.setUserName("张三");
//        user.setPassWord("123456");
//        user.setSex("男");
//        user.setPhone("5757262");

        return userService.addUser(user);
    }


    @PutMapping("/testAnnotation")
    @ApiOperation(value = "测试自定义注解的使用")
    public void testAnnotation() {
        User user = new User();
        Class<?> userClass = user.getClass();

        //遍历实体类所有字段
        for (Field field : userClass.getDeclaredFields()) {
            //判断字段是否被 @Name注解标记
            if (field.isAnnotationPresent(Name.class)) {
                //获取@Name实例
                Name annotation = field.getAnnotation(Name.class);

                //获取注解值
                String value = annotation.value();
                System.out.println(value);
                //设置属性值
                try {
                    field.setAccessible(true);//设置访问权限，以便访问私有属性
                    field.set(user, value);//赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            //判断字段是否被 @Sex注解标记
            if (field.isAnnotationPresent(Sex.class)) {
                Sex annotation = field.getAnnotation(Sex.class);
                //获取注解值
                String s = annotation.gender().toString();
                System.out.println(s);
                //设置属性值
                try {
                    field.setAccessible(true);//设置访问权限，以便访问私有属性
                    field.set(user, s);//赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(user);
    }
}
