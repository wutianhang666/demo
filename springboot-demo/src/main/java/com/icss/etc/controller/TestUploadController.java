package com.icss.etc.controller;

import com.icss.etc.common.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "测试文件上传，展示", description = "测试文件上传，展示")
@RequestMapping("/testUpload")
public class TestUploadController {

    //上传头像
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public Object upload(@RequestPart(value = "file") MultipartFile file) throws IOException {

        try {
            //获取图片名称
            String fileName = file.getOriginalFilename();
            // 指定保存路径
            String filePath = "D:/photo/" + fileName;
            //将文件保存到本地
            file.transferTo(new File(filePath));
            return BaseResult.success("操作成功");
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResult.failResultCreate("上传失败");
        }
    }

}
