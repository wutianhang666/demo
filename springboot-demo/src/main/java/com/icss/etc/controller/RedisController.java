package com.icss.etc.controller;

import com.icss.etc.pojo.User;
import com.icss.etc.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "用户管理")
@RequestMapping("/user")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/setString/{key}/{value}")
    @ApiOperation(value = "restful风格传参测试Redis存数据")
    public void restfulSetString(@PathVariable String key, @PathVariable String value) {
        redisService.setString(key, value);
    }

    @GetMapping(value = "/setString")
    @ApiOperation(value = "测试Redis存数据")
    public String setString(@RequestParam(value = "key", required = false) String key,
                          @RequestParam(value = "value", required = false) String value) {
        logger.info("测试Redis存数据", key, value);
        return redisService.setString(key, value);
    }

    @GetMapping("/getString")
    @ApiOperation(value = "测试Redis取数据")
    public String getString(String key) {
        String name = redisService.getString(key);
        logger.info("传入的key值：" + key);
        logger.info("获取到的name值：" + name);
        return name;
    }
}
