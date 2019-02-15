package com.example.util;


import com.alibaba.fastjson.JSONObject;
import com.example.domain.AuthToken;

/**
 * @author yangbo
 * 工具类
 * 2016年12月21日 下午1:58:38
 */
public class WechatUtil {

	public WechatUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     * 获得accessKey
     * @param appId
     * @param appSecret
     * @return
     */

    public  static AuthToken GetAccessKey (String appId, String appSecret){
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        AuthToken authToken=null;
        if (null != jsonObject) {
            try {
                authToken = new AuthToken();
                authToken.setAccessToken(jsonObject.getString("access_token"));
                authToken.setExpires_in(jsonObject.getInteger("expires_in"));

            } catch (Exception e) {
                authToken = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println(errorCode);
                System.out.println(errorMsg);
            }
        }
        return  authToken;
    }
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
	 * @return 
     * @return WeixinAouth2Token
     */
   /* public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println(errorCode);
                System.out.println(errorMsg);
              //  log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }*/
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
   /* public static UserWechatDO getWechatInfo(String accessToken, String openId, String refreshToken) {
        UserWechatDO userWechatDO = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (!StringUtil.isEmpty(jsonObject)) {
            try {
                userWechatDO = new UserWechatDO();
                // 用户的标识
                userWechatDO.setWechatOpenid(jsonObject.getString("openid"));
                // 昵称
                String nickname = jsonObject.getString("nickname");
                nickname = filterEmoji(nickname);
                userWechatDO.setNickName(nickname);
                // 性别（1是男性，2是女性，0是未知）
                userWechatDO.setSex(jsonObject.getInteger("sex"));
                // 用户所在国家
                userWechatDO.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                userWechatDO.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                userWechatDO.setCity(jsonObject.getString("city"));
                // 用户头像
                userWechatDO.setHeadImgUrl(jsonObject.getString("headimgurl"));
                //UnicodeID
                userWechatDO.setWechatUnionid(jsonObject.getString("unionid"));
                //userWechatDO.setWin(0);
                //userWechatDO.setLose(0);
                //查询时间
          //      Date date = new Date();
               // player.setJoinTime(date);
                //首次授权时间
                userWechatDO.setCreateTime(new Date());
                //更新时间
                userWechatDO.setUpdateTime(new Date());
                userWechatDO.setLasttIme(new Date());
                //凭证保存
                userWechatDO.setAccessToken(accessToken);
                //刷新凭证
                userWechatDO.setRefreshToken(refreshToken);
                // 用户特权信息
             //   snsUserInfo.setPrivilegeList(JSONArray.parseObject(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                userWechatDO = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println(errorCode);
                System.out.println(errorMsg);
               // log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return userWechatDO;
    }*/
    
    //检验凭证是否失效
	@SuppressWarnings("unused")
	public static boolean  judgeToken(String accessToken, String openId){
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        int errorCode = jsonObject.getInteger("errcode");
        String errorMsg = jsonObject.getString("errmsg");//正确返回OK
        errorMsg = errorMsg.toUpperCase();
        if(errorMsg.equals("OK")){
        	return true;
        }
        return false;
    }

	
    
    //去掉ios特殊字符
  /*  public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
                }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }*/
    //判断特殊字符串
    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
