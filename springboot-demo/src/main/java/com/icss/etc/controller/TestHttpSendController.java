package com.icss.etc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.User;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@Api(value = "http管理", description = "http管理")
@RequestMapping("/api/http")
public class TestHttpSendController {

    @Test
    public void testHttp() {
        User user = new User();
        user.setId("7dcade79-7c3d-4f1c-820b-ec747e4b122a");

        String newData = JSON.toJSONString(user);
        String url = "http://localhost:9130/v1/les/tsTaskInfo/taskFinish";

        BaseResult baseResult = callInterface(newData, url, "PUT");
    }

    public BaseResult callInterface(String data, String urls, String requestMethod) {

        BaseResult baseResult = new BaseResult();

        //调用接口
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";

        try {
            URL url = new URL(urls);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE6.0;WindowsNT5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
            conn.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);//GET和POST必须全大写

            /**
             * 下面的三句代码，就是调用第三方http接口
             */
            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);//获取URLConnection对象对应的输出流
            //调用接口post
            out.write(data);//发送请求参数即数据
            System.out.println("====请求参数" + data);
            out.flush();//缓冲数据

            /**
             *下面的代码相当于，获取调用第三方http接口后返回的结果
             */
            InputStream is = conn.getInputStream();//获取URLConnection对象对应的输入流
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));//构造一个字符流缓存
            String str = "";
            while ((str = br.readLine()) != null) {
                result += str;
            }

            System.out.println("====返回值：" + result);

            //转换json
            JSONObject objJson = JSONObject.parseObject(result);
            System.out.println("=====objJson" + objJson);
            String code = objJson.getString("code");

            System.out.println("=====code：" + code);

            is.close();//关闭流
            conn.disconnect();//断开连接，disconnect是在底层tcpsocket链接空闲时才切断，如果正在被其他线程使用就不切断。
        } catch (Exception e) {
            System.out.println("============" + e.getMessage());
            System.out.println("============" + e.getMessage());
            System.out.println("============" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baseResult;
    }
}
