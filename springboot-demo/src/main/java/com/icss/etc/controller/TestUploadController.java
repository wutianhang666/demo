package com.icss.etc.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "测试文件上传，展示", description = "测试文件上传，展示")
@RequestMapping("/upload")
public class TestUploadController {

    //上传头像
    @PostMapping("/upload")
    public Object upload(@RequestPart MultipartFile multipartFile) throws IOException {

        //获取图片名称
        System.out.println(multipartFile.getOriginalFilename());
        //将上传的图片保存
        multipartFile.transferTo(new File("E:/ceshi/"+multipartFile.getOriginalFilename()));

        return "success 上传成功";
    }

}
