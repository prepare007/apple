package com.example.util;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * http 发送post请求工具类
 * @author xw
 */
public class HttpsUtil {

	
	public static Map<String,Object> httpsPost(String curl, Map<String, Object> requestBody, 
			Map<String, String> requestHeader, String requestMethod) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(curl);
			// 打开和URL之间的连接
			HttpURLConnection  conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "text/json");
			if(requestHeader!=null && !requestHeader.isEmpty()) {
				Set<String> keySet = requestHeader.keySet();
				//将参数放入请求头
				for (String key : keySet) {
					conn.setRequestProperty(key, requestHeader.get(key));
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取输出流
			// 发送请求参数
			if(requestBody!=null && !requestBody.isEmpty()) {
				out = new PrintWriter(conn.getOutputStream());
				String param = JSONObject.toJSONString(requestBody);
				out.print(param);
				out.flush();
			}
			int responseCode = conn.getResponseCode();
			// flush输出流的缓冲
			// 定义BufferedReader输入流来读取URL的响应
			InputStream inputStream = null;
			try {
				inputStream = conn.getInputStream();
			} catch (Exception e) {
			}
			if(null != inputStream) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			}else {
				result = "{'error':'chatroom not found'}";
			}
			resultMap.put("result", result);
			resultMap.put("responseCode", responseCode);
		} catch (Exception e) {
			System.out.println("发送请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resultMap;
	}
	//防止微信回调域名影响
	/*@SuppressWarnings({ "unused", "rawtypes" })
	public static Map<String,Object> httpsPost(Map<String, Object> requestBody,String accessToken){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		//创建实例
		Client client = Client.create();
		//封装请求资源，发送请求
		WebResource webResource = client.resource("https://a1.easemob.com/1103161203178227/wechat-room/users");
       //封装参数
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		JSONObject jsonp = new JSONObject();
		String temp = null;
	     Set set = requestBody.keySet();
	        for (Object key : set) {
	        	    queryParams.add((String) key, requestBody.get(key));
					jsonp.put((String)key, requestBody.get(key));
				      temp=jsonp.toString();
			//	 ByteArrayInputStream bais = new ByteArrayInputStream(temp.getBytes());
	        }
	//String  outStem=webResource.header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_FORM_URLENCODED ).post(String.class,temp);
    //String  outStem=webResource.entity(temp).header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_FORM_URLENCODED ).post(String.class);
	ClientResponse response = webResource.header("Authorization", "Bearer " + accessToken).accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_FORM_URLENCODED ).post(ClientResponse.class,temp);
	//EntityTag e = response.getEntityTag();  
	    String entity = response.getEntity(String.class);  
	    int responseCode = response.getStatus();
		resultMap.put("result", entity);
		resultMap.put("responseCode", responseCode);
		System.out.println(entity+"--->"+responseCode);
		return resultMap;
	}*/
	
}
