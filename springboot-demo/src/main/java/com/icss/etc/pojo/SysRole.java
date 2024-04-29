package com.icss.etc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "SYS_ROLE")
public class SysRole {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY")
    protected String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_DATE")
    protected Date createDate;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY")
    protected String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_DATE")
    protected Date updateDate;

    @ApiModelProperty(value = "角色编码")
    protected String code;

    @ApiModelProperty(value = "角色名称")
    protected String name;

    @ApiModelProperty(value = "备注")
    protected String remarks;
}
