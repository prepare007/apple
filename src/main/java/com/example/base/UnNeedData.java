/*
 * Copyright (c) KYE 2019, All Rights Reserved
 */

package com.example.base;

/**
 * Routing  * Desc
 * 不需要data数据的返回对象
 * Source  *
 * Created by LWS on 2018/11/6 14:17
 * Version 1.0
 */
public class UnNeedData {

    private String msg = "";

    public UnNeedData() {

    }

    public UnNeedData(String msg) {

        this.msg = msg;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }
}