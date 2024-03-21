package com.icss.etc.pojo;

import com.icss.etc.annotation.Name;
import com.icss.etc.annotation.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "用户实体类")
public class User {

    @ApiModelProperty(value = "用户唯一标识")
    private String id;

    @ApiModelProperty(value = "用户姓名")
    @Name(value = "zhangSan")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String passWord;

    @ApiModelProperty(value = "性别")
    @Sex(gender = Sex.Type.Man)
    private String sex;

    @ApiModelProperty(value = "电话")
    private String phone;
}
