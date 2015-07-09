package com.doudou.test;

import com.doudou.utils.LoginResult;
import com.doudou.utils.RongYunUtils;
import com.doudou.utils.SdkHttpResult;
import com.google.gson.Gson;

public class TestGetToken {
public static void main(String[] args) {
	try {
		//result:{"code":200,"userId":"abc","token":"imC0PuXA2kCrSN6kROu/FspwuMmViZynEoKlWNWp3KQomyXFbgTQ2xgpBU0oEB3bLtZ30nXGbRM="}
		
		SdkHttpResult result = new RongYunUtils().getToken("abc", "", "");
		System.out.println("result:"+result.getResult().toString());
		LoginResult loginResult =new Gson().fromJson(result.getResult().toString(), LoginResult.class);
	    System.out.println(loginResult.token);
	
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
}
}
