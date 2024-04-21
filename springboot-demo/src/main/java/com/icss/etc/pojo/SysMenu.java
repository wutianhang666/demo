package com.icss.etc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.List;

@Data
//可以使用 @TableName 表名注解指定当前实体类对应的表名，比如下面 Menu 实体类对应表名为 sys_menu
@TableName(value = "SYS_MENU")
public class SysMenu {

    //可以使用 @TableId 注解（标注在主键上）和 @TableField 注解（标注在其他成员属性上）来指定对应的字段名
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "NAME")
    private String name;

    @TableField(value = "PATH")
    private String path;

    @TableField(value = "ICON")
    private String icon;

    @TableField(value = "DESCRIPTION")
    private String description;

    //在数据表中没有children这个字段，这个在做菜单的时候会用到，所以使用exist=false忽略
    @TableField(exist = false)
    private List<SysMenu> children;

    @TableField(value = "PARENT_ID")
    private Integer parentId;

    @TableField(exist = false)
    private String parentName;

    //这样处理的主要目的是java对带有下划线的字段不识别，所以改为驼峰形式
    @TableField(value = "PAGE_PATH")
    private String pagePath;

    @TableField(value = "SORT")
    private Integer sort;
}
