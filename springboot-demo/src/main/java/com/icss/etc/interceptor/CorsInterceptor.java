package com.icss.etc.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsInterceptor implements Filter {

    //跨域测试，不好用
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源的跨域请求
//        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // 允许的HTTP方法
//        httpResponse.setHeader("Access-Control-Allow-Headers", "*"); // 允许的所有请求头
//        chain.doFilter(request, response);
    }
}
