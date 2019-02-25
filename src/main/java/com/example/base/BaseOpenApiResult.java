/*
 * Copyright (c) KYE 2019, All Rights Reserved
 */

package com.example.base;

/**
 * Routing
 * Desc    result返回基本类
 * Source
 * Created by LWS on 2018/11/5 14:56
 * Version 1.0
 */

public class BaseOpenApiResult<T> {

    /**
     * code : 0
     * msg : OK
     * data : {"retStatus":0,"errCode":"002","errMsg":"手机号未注册"}
     * success : true
     */

    private int code;

    private String msg = "";

    private T data;

    private boolean success;

    public int getCode() {

        return code;
    }

    public BaseOpenApiResult setCode(int code) {

        this.code = code;
        return this;
    }

    public String getMsg() {

        return msg;
    }

    public BaseOpenApiResult setMsg(String msg) {

        this.msg = msg;
        return this;
    }

    public T getData() {

        return data;
    }

    public BaseOpenApiResult setData(T data) {

        this.data = data;
        return this;
    }

    public boolean isSuccess() {

        return success;
    }

    public BaseOpenApiResult setSuccess(boolean success) {

        this.success = success;
        return this;

    }
}
