package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/wechat")
public class wechatController {
    private String   TOKEN="2019218sun";
    @RequestMapping(value = "/validateToken")
    public void validateToken(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce"); //随机数
        String echostr = req.getParameter("echostr");//随机字符串

        String sortStr = sort(TOKEN, timestamp, nonce);
        /**
         * 字符串进行shal加密
         */
        String mySignature = shal(sortStr);
        /**
         * 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
         */
        if (!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)) {
            System.out.println("-----签名校验通过-----");
            resp.getWriter().write(echostr);
        } else {
            System.out.println("-----校验签名失败-----");
        }


    }

    /**
     * 参数排序
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 字符串进行shal加密
     *
     * @param str
     * @return
     */
    public String shal(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}