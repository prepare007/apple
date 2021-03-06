package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:config/weixin.properties" }, encoding="UTF-8")
public class WechatConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wechat_app_id}")
    private String wechatAppId;

    @Value("${wechat_app_secret}")
    private String wechatAppSecret;

    @Value("${encoding_aes_key}")
    private String encoding_aes_key;

    public String getEncoding_aes_key() {
        return encoding_aes_key;
    }

    public void setEncoding_aes_key(String encoding_aes_key) {
        this.encoding_aes_key = encoding_aes_key;
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId;
    }

    public String getWechatAppSecret() {
        return wechatAppSecret;
    }

    public void setWechatAppSecret(String wechatAppSecret) {
        this.wechatAppSecret = wechatAppSecret;
    }
}
