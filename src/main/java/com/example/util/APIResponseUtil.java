/*
 * Copyright (c) KYE 2019, All Rights Reserved
 */

package com.example.util;

import com.example.base.BaseListBean;
import com.example.base.BaseOpenApiResult;
import com.example.base.UnNeedData;
import com.example.constant.Constants;

import java.util.List;

/**
 * Routing
 * Desc    TODO
 * Source
 * Created by LWS on 2019/2/25 10:35
 * Version 1.0
 */

public class APIResponseUtil {

    //成功，不需要返回参数
    public static <T> BaseOpenApiResult<T> makeOKRsp() {

        return new BaseOpenApiResult<T>()
                .setCode(Constants.ResponseCode.SUCCESS)
                .setMsg(Constants.ResponseMessage.SUCCESS)
                .setData(new UnNeedData())
                .setSuccess(true);
    }

    //成功，只返回提示信息
    public static <T> BaseOpenApiResult<T> makeOKRsp(String msg) {

        return new BaseOpenApiResult<T>()
                .setCode(Constants.ResponseCode.SUCCESS)
                .setMsg(Constants.ResponseMessage.SUCCESS)
                .setData(new UnNeedData(msg))
                .setSuccess(true);
    }

    //成功，返回参数
    public static <T> BaseOpenApiResult<T> makeOKRsp(T data) {

        return new BaseOpenApiResult<T>()
                .setCode(Constants.ResponseCode.SUCCESS)
                .setMsg(Constants.ResponseMessage.SUCCESS)
                .setData(data)
                .setSuccess(true);
    }

    //成功，返回List参数
    public static <T> BaseOpenApiResult<T> makeOKRsp(List<T> data) {

        return new BaseOpenApiResult<T>()
                .setCode(Constants.ResponseCode.SUCCESS)
                .setMsg(Constants.ResponseMessage.SUCCESS)
                .setData(new BaseListBean(data))
                .setSuccess(true);
    }

    //失败，只返回提示信息
    public static <T> BaseOpenApiResult<T> makeErrRsp(String message) {

        return new BaseOpenApiResult<T>()
                .setCode(Constants.ResponseCode.FAIL)
                .setMsg(Constants.ResponseMessage.FAIL)
                .setData(new UnNeedData(message))
                .setSuccess(false);
    }

    //自定义返回code，无返回参数
    public static <T> BaseOpenApiResult<T> makeRsp(int code, String msg,boolean success) {

        return new BaseOpenApiResult<T>()
                .setCode(code)
                .setMsg(msg)
                .setSuccess(success);
    }

    //自定义返回code，有返回参数
    public static <T> BaseOpenApiResult<T> makeRsp(int code, String msg, T data) {

        return new BaseOpenApiResult<T>()
                .setCode(code)
                .setMsg(msg)
                .setData(data);
    }

    //自定义返回code，有返回List参数
    public static <T> BaseOpenApiResult<T> makeRsp(int code, String msg, List<T> data) {

        return new BaseOpenApiResult<T>()
                .setCode(code)
                .setMsg(msg)
                .setData(new BaseListBean(data));
    }
}
