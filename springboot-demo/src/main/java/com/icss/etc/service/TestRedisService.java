package com.icss.etc.service;

public interface TestRedisService {

    public String setString(String key, String value);

    public String getString(String key);
}
