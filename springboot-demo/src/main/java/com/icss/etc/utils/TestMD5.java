package com.icss.etc.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

/**
 * @program: etc
 * @description: 测试MD5加密工具类
 * @create: 2020-12-09 16:04
 **/
public class TestMD5 {

    @Test
    public void test() {
        String str_pwd="123456";
        String md5Hex1 = DigestUtil.md5Hex(str_pwd);
        System.out.println(md5Hex1);

        String str_login_pwd="123456";
        String md5Hex2 = DigestUtil.md5Hex(str_login_pwd);
        System.out.println(md5Hex2);

        String md5Hex3 = SecureUtil.md5(str_login_pwd);
        System.out.println(md5Hex3);

        System.out.println(md5Hex1.equals(md5Hex2));
    }
}
