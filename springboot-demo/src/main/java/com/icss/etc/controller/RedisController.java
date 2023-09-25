package com.icss.etc.controller;

import com.alibaba.fastjson.JSON;
import com.icss.etc.pojo.User;
import com.icss.etc.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Redis功能测试", description = "Redis功能测试")
@RequestMapping("/user")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @GetMapping("/setPojo")
    @ApiOperation(value = "测试存对象")
    public String setPojo() {
        logger.info("测试存对象");
        User user = new User("11", "zhangsan", "111222", "nv", "15998777777");

        // 如果是个实体，我们可以使用json工具转成json字符串
        redisService.setString("user", JSON.toJSONString(user));
        logger.info("用户信息：" + redisService.getString("user"));
        return redisService.getString("user");
    }

    @GetMapping("/setGetHash")
    @ApiOperation(value = "测试存取Hash")
    public void setGetHash(String key, String filedKey, String value) {

        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put(key, filedKey, value);
        logger.info("存入Hash成功");

        String returnValue = (String) stringRedisTemplate.opsForHash().get(key, filedKey);
        logger.info("取出的Hash：" + returnValue);
    }

    @GetMapping("/setGetList")
    @ApiOperation(value = "测试存取List")
    public void setGetList() {
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPush("list", "value1");
        listOperations.leftPush("list", "value2");
        listOperations.leftPush("list", "value3");
        logger.info("存入成功");

        List<String> list = stringRedisTemplate.opsForList().range("list", 0, -1);
        logger.info("取出的list " + list);
    }
}
