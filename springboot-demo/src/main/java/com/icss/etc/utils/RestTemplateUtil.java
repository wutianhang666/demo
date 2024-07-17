package com.icss.etc.utils;

import com.alibaba.fastjson.JSONObject;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate调用远程接口
 */
@Component
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以get方式请求第三方http接口 getForEntity
     *
     * @param url
     * @return
     */
    public BaseResult doGetWith1(String url) {
        ResponseEntity<BaseResult> responseEntity = restTemplate.getForEntity(url, BaseResult.class);
        BaseResult result = responseEntity.getBody();
        return result;
    }

    /**
     * 以get方式请求第三方http接口 getForObject
     * 返回值返回的是响应体，省去了我们再去getBody()
     *
     * @param url
     * @return
     */
    public BaseResult doGetWith2(String url) {
        BaseResult result = restTemplate.getForObject(url, BaseResult.class);
        return result;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     *
     * @param url
     * @param user
     * @return
     */
    public String doPostWith1(String url, User user) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, user, String.class);
        String body = responseEntity.getBody();
        return body;
    }

    /**
     * 以post方式请求第三方http接口 postForEntity
     * 返回值返回的是响应体，省去了我们再去getBody()
     *
     * @param url
     * @param user
     * @return
     */
    public String doPostWith2(String url, User user) {
        String body = restTemplate.postForObject(url, user, String.class);
        return body;
    }

    /**
     * exchange
     *
     * @return
     */
    public String doExchange(String url, Integer age, String name) {
        //header参数
        HttpHeaders headers = new HttpHeaders();
        String token = "asdfaf2322";
        headers.add("authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //放入body中的json参数
        JSONObject obj = new JSONObject();
        obj.put("age", age);
        obj.put("name", name);

        //组装
        HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        String body = responseEntity.getBody();
        return body;
    }
}
