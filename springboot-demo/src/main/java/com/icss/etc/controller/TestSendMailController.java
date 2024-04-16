package com.icss.etc.controller;

import com.icss.etc.service.TestSendMailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试发邮件", description = "测试邮件")
@RequestMapping("/mail")
public class TestSendMailController {

    @Autowired
    private TestSendMailService sendMailService;

    @GetMapping("/send")
    public Object sendMail() {
        return sendMailService.testSendMail();
    }
}
