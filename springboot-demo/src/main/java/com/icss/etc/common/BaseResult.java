package com.icss.etc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {

    private T data;
    private int code;
    private String message;

    public static BaseResult success(Object data){
        return new BaseResult(data, 1, "操作成功");
    }
}
