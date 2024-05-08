package com.icss.etc.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.icss.etc.common.BaseResult;
import com.icss.etc.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/exportExcel")
    @ApiOperation(value = "excel导出")
    public void exportExcel(HttpServletResponse response) {

        try {
            //第一个Sheet页数据
            List<User> studentList = new ArrayList<>();
            User user1 = new User("1", "张1", "111", "男", "5858634");
            User user2 = new User("2", "张2", "111", "男", "5858585");
            studentList.add(user1);
            studentList.add(user2);

            //第二个Sheet页数据
            List<User> studentList2 = new ArrayList<>();
            User user3 = new User("3", "张3", "111", "男", "5675678");
            User user4 = new User("4", "张4", "111", "男", "6567890");
            studentList2.add(user3);
            studentList2.add(user4);

            //使用easyExcel需要将导出文件格式改为.xlsx，要不然导出文件打开回报“文件损坏或文件扩展名异常”
            String fileName = new String("文件名称.xlsx");
            //文件名格式需要使用这种格式，否则也会异常
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.addHeader("Content-Disposition", "filename=" + fileName);
            //设置类型，扩展名为.xls
            response.setContentType("application/vnd.ms-excel");
            //将数据写入sheet页中,其中.head方法是表头名称，对应类加了注解可以生成的
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "1.校本人员统计").head(User.class).build();
            WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "2.基本情况").head(User.class).build();

            excelWriter.write(studentList, writeSheet1);
            excelWriter.write(studentList2, writeSheet2);
            excelWriter.finish();
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
