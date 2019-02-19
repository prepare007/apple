package com.example.controller;

import com.example.config.WechatConfig;
import com.example.domain.AuthToken;
import com.example.util.MessageUtil;
import com.example.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(value = "/wechat")
public class wechatController {
    private String TOKEN = "2019218sun";
    @Autowired
    private WechatConfig wechatConfig;
    @RequestMapping(value = "/id")
    public String config(Model model) {

        model.addAttribute("id", wechatConfig.getWechatAppId());
        return "index";
    }

    @RequestMapping(value = "/getAccessToken")
    public String getAccessToken(Model model) {

        String appid = wechatConfig.getWechatAppId();
        String secret = wechatConfig.getWechatAppSecret();
        AuthToken authToken = WechatUtil.GetAccessKey(appid, secret);
        String assccesToken = authToken.getAccessToken();
        model.addAttribute("assccesToken",assccesToken);
        return "index";
    }

    /**
     * post方法处理业务逻辑 ，validateToken 这个要和公众号的服务器配置的url一致
     * @param req
     * @param resp
     * @throws UnsupportedEncodingException
     */
    @RequestMapping (value = "/validateToken",method = {RequestMethod.POST })
    public  void  text(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String result="";

        try {
            Map<String,String> map=MessageUtil.parseXml(req);
            System.out.println("开始构造消息");

            result = MessageUtil.buildResponseMessage(map);
            System.out.println(result);
            if(result.equals("")){
                result = "未正确响应";
            }
            resp.getWriter().println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * get方法校验签名
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "/validateToken",method = {RequestMethod.GET })
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
        PrintWriter out=null;
        if (!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)) {
            System.out.println("啦啦啦，签名校验通过-----");
            try {
                out = resp.getWriter();
                out.print(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (out != null) {
                    out.close();
                    out = null;                       //释放资源
                }
            }
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