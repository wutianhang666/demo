package com.icss.etc.utils;

import org.junit.Test;

import java.util.Base64;

/**
 * @program: etc
 * @description: 测试base64加密解密工具类
 * @create: 2020-12-09 16:04
 **/
public class TestBase64 {

    @Test
    public void test() {

        String name = "张三";

        //编码
        String encodeToString = Base64.getEncoder().encodeToString(name.getBytes());
        System.out.println(encodeToString);

        //解码
        byte[] decode = Base64.getDecoder().decode(encodeToString);
        System.out.println(new String(decode));
    }
}
