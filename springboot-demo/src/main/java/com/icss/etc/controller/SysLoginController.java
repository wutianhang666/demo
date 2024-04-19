package com.icss.etc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.SysUser;
import com.icss.etc.service.SysUserService;
import com.icss.etc.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Api(value = "登录接口", description = "登录接口")
@RequestMapping(value = "/sys")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResult login(@RequestParam String loginName, @RequestParam String password) throws UnsupportedEncodingException {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("LOGIN_NAME", loginName);
        wrapper.eq("PASSWORD", password);
        List<SysUser> list = sysUserService.selectList(wrapper);
        if (list.isEmpty()) {
            return BaseResult.failResultCreate("登录名或密码错误");
        }

        //登录成功则调用JWTUtil类的创建Token方法返回客户端
        String token = JWTUtil.createToken(list.get(0));
        return BaseResult.success(token);
    }

    @ApiOperation("JWT获取token")
    @PostMapping("/getToken")
    public String getToken() throws UnsupportedEncodingException {
        SysUser user = new SysUser();
        user.setLoginName("张三");
        user.setPassword("66666");
        String token = JWTUtil.createToken(user);
        return token;
    }
}
