package com.doudou.utils;


public class SdkHttpResult {
	private int code;
	private String result;

	public SdkHttpResult(int code, String result) {
		this.code = code;
		this.result = result;
	}

	public int getHttpCode() {
		return code;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return String.format("{\"code\":\"%s\",\"result\":%s}", code,
				result);
	}
}
