/*
 * Copyright (c) KYE 2019, All Rights Reserved
 */

package com.example.constant;

/**
 * Routing
 * Desc    TODO
 * Source
 * Created by LWS on 2018/11/5 15:06
 * Version 1.0
 */

public interface Constants {

    /**
     * 响应吗
     */
    interface ResponseCode {

        int SUCCESS               = 0;          // 操作成功
        int FAIL                  = -1;         // 操作失败
        int UNAUTHORIZED          = 401;      // 未认证
        int NOT_FOUND             = 404;      //接口不存在
        int INTERNAL_SERVER_ERROR = 500;      // 服务器内部错误
    }


    /**
     * 响应信息
     */
    interface ResponseMessage {

        String SUCCESS               = "操作成功";          // 操作成功
        String FAIL                  = "操作失败";         // 操作失败
        String UNAUTHORIZED          = "未认证";      // 未认证
        String NOT_FOUND             = "接口不存在";      //接口不存在
        String INTERNAL_SERVER_ERROR = "服务器内部错误";      // 服务器内部错误
    }
}
