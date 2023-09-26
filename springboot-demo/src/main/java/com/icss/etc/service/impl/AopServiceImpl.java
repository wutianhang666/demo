package com.icss.etc.service.impl;

import com.icss.etc.service.AopService;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {

    @Override
    public void testAop() {
        System.out.println("我是主程序");
    }
}
