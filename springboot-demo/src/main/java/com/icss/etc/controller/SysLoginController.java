package com.icss.etc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.SysUser;
import com.icss.etc.service.SysUserService;
import com.icss.etc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public BaseResult login(@RequestParam String loginName, @RequestParam String password) throws UnsupportedEncodingException {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("LOGIN_NAME", loginName);
        wrapper.eq("LOGIN_NAME", loginName);
        List<SysUser> list = sysUserService.selectList(wrapper);
        if (list.isEmpty()) {
            return BaseResult.failResultCreate("登录名或密码错误");
        }

        //登录成功则调用JWTUtil类的创建Token方法返回客户端
        String token = JWTUtil.createToken(list.get(0));
        return BaseResult.success(token);
    }
}
