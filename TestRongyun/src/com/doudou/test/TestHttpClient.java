package com.doudou.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.doudou.utils.HttpsClient;

public class TestHttpClient {

	private static final String UTF8 = "UTF-8";
	public static void main(String[] args) throws IOException {
		
		String userId ="12344555";
		String userName="huhuhu";
		String portraitUri="";
		HttpsClient client =new HttpsClient();
		/*Map<String, String> reqProperty = new HashMap<String,String>();
		reqProperty.put("userId", URLEncoder.encode(userId, UTF8));
		reqProperty.put("name", URLEncoder.encode(userName, UTF8));
		reqProperty.put("portraitUri", URLEncoder.encode(portraitUri, UTF8));
		client.setRequestProperty(reqProperty);
		*/
		
		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
		
		String result=client.doPost("https://api.cn.ronghub.com/user/getToken.json", sb.toString());
		System.out.println("result:"+result);
		
	}

}
