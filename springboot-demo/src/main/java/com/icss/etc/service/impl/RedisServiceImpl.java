package com.icss.etc.service.impl;

import com.icss.etc.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * set redis: string类型
     *
     * @param key   key
     * @param value value
     */
    public String setString(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
        return "存入成功";
    }

    /**
     * get redis: string类型
     *
     * @param key key
     * @return
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
