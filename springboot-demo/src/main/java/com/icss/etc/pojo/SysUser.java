package com.icss.etc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
//可以使用 @TableName 表名注解指定当前实体类对应的表名，比如下面 Menu 实体类对应表名为 sys_menu
@TableName(value = "SYS_USER")
public class SysUser {

    //可以使用 @TableId 注解（标注在主键上）和 @TableField 注解（标注在其他成员属性上）来指定对应的字段名
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "NAME")
    private String name;

    @ApiModelProperty(value = "登录名")
    @TableField(value = "LOGIN_NAME")
    private String loginName;

    @ApiModelProperty(value = "密码")
    @TableField(value = "PASSWORD")
    private String password;

    @ApiModelProperty(value = "工号")
    @TableField(value = "NO")
    private String no;

    @ApiModelProperty(value = "电话")
    @TableField(value = "PHONE")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField(value = "EMAIL")
    private String email;

    @ApiModelProperty(value = "是否可登录")
    @TableField(value = "LOGIN_FLAG")
    private String loginFlag;

    @ApiModelProperty(value = "用户是否被锁定")
    @TableField(value = "LOCK_FLAG")
    private String lockFlag;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "ROLE_ID")
    private Integer roleId;
}