package com.doudou.utils;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class RongYunUtils {
	                                           // https://api.cn.ronghub.com/
    private static final String RONGCLOUDURI = "https://api.cn.ronghub.com";
	private static final String appKey="cpj2xarlj5k5n";
	private static final String appSecret="z8sYmZOaFOcPh";
	private static final String UTF8 = "UTF-8";
	
	// 获取token
		public  SdkHttpResult getToken(
				String userId, String userName, String portraitUri
				) throws Exception {

			HttpURLConnection conn = HttpUtil
					.CreatePostHttpConnection(appKey, appSecret, RONGCLOUDURI
							+ "/user/getToken." + "json");

			StringBuilder sb = new StringBuilder();
			sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
			sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
			sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}
	
}
