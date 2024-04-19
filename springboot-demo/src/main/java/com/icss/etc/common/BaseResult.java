package com.icss.etc.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象
 */
@Data
public class BaseResult<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public BaseResult() {
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResult success(Object data){
        return new BaseResult(1, "操作成功", data);
    }

    public static BaseResult failResultCreate(String message){
        return new BaseResult(0, message, null);
    }

    public static BaseResult failResultCreate(String message, Object date){
        return new BaseResult(0, message, date);
    }

}
