package com.icss.etc.service.impl;

import com.icss.etc.service.TestAopService;
import org.springframework.stereotype.Service;

@Service
public class TestAopServiceImpl implements TestAopService {

    @Override
    public void testAop() {
        System.out.println("我是主程序");
    }
}
