package com.icss.etc.service;

public interface RedisService {

    public String setString(String key, String value);

    public String getString(String key);
}
