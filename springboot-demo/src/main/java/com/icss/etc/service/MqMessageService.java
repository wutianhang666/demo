package com.icss.etc.service;

public interface MqMessageService {

    void sendMessage(String message);

    String doMessage();
}
