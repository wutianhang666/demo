package com.icss.etc.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.icss.etc.common.BaseResult;
import com.icss.etc.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求URL
        String url = request.getRequestURL().toString();
        log.info("得到请求路径:{}", url);

        //2.判断url是否包含login,有则放行
        if (url.contains("/login")) {
            return true;
        }

        //3.获取请求头中的令牌
        String token = request.getHeader("token");
        log.info("从请求头中获取token：{}", token);

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");
            //创建响应结果对象
            BaseResult responseResult = new BaseResult().failResultCreate("未登录");
            //把result对象转为json字符串
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json; charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JWTUtil.getUsername(token);
        } catch (Exception e) {
            log.info("令牌解析失败！");

            //创建响应结果
            BaseResult responseResult = new BaseResult().failResultCreate("未登录");

            //把result转化为json
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json; charset=utf-8");

            //响应
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
}

