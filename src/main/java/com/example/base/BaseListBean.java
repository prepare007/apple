/*
 * Copyright (c) KYE 2019, All Rights Reserved
 */

package com.example.base;

import java.util.List;

/**
 * Routing
 * Desc    TODO
 * Source
 * Created by LWS on 2018/11/6 14:12
 * Version 1.0
 */

public class BaseListBean<T> {

    private List<T> data;

    public BaseListBean(List<T> data) {

        this.data = data;
    }

    public List<T> getData() {

        return data;
    }

    public void setData(List<T> data) {

        this.data = data;
    }
}
