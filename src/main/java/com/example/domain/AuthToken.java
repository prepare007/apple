package com.example.domain;

public class AuthToken {

    // 网页授权接口调用凭证
    private String accessToken;
    //过期时间
    private int  expires_in;

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
