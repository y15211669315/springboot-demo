package com.springbootwx.dto;

import java.io.Serializable;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 响应类
 * @date 2018/7/16 14:32
 */
public class _ResultDto implements Serializable {

    private int code;

    private String message = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
