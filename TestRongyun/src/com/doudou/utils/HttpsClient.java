package com.doudou.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class HttpsClient {
	   //读超时30s  
    private final int timeout = 30000;  
      
    private Map<String, String>   reqProperty = null;  
    private String reqEncode = "UTF-8";  
    private String respEncode = "UTF-8";  
      
    /**
     * xiaoxi baotou 
     */
	private static final String APPKEY = "RC-App-Key";
	private static final String NONCE = "RC-Nonce";
	private static final String TIMESTAMP = "RC-Timestamp";
	private static final String SIGNATURE = "RC-Signature";

	// value shuzhi 
    private static final String RONGCLOUDURI = "https://api.cn.ronghub.com";
	private static final String appKey="cpj2xarlj5k5n";
	private static final String appSecret="z8sYmZOaFOcPh";
	private static final String UTF8 = "UTF-8";
	
	
	
    
      
    public HttpsClient(){  
        String trustStore = System.getProperty("javax.net.ssl.trustStore");  
          
        //抑制证书域名与实际域名不匹配的警告  
        HostnameVerifier hv = new HostnameVerifier() {  
  
            public boolean verify(String urlHostName, SSLSession session) {  
                System.out.println("Warning: URL Host: " + urlHostName  
                + " vs. " + session.getPeerHost());  
                return true;  
            }  
  
        };  
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        
        String nonce = String.valueOf(Math.random() * 1000000);
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		StringBuilder toSign = new StringBuilder(appSecret).append(nonce)
				.append(timestamp);
		String sign = CodeUtil.hexSHA1(toSign.toString());

        //初始化请求参数  
        reqProperty = new HashMap<String, String>();  
        reqProperty.put("Content-Type", "text/xml");  
        reqProperty.put(APPKEY, appKey);
        reqProperty.put(NONCE, nonce);
        reqProperty.put(TIMESTAMP, timestamp);
        reqProperty.put(SIGNATURE, sign);
        reqProperty.put("Content-Type",
				"application/x-www-form-urlencoded");
        
    }  
      
    //设置请求参数  
    public void setRequestProperty(Map<String, String> reqProperty) {  
        this.reqProperty.putAll(reqProperty);  
    }  
      
    public String doGet(String urlstr) throws IOException {  
        URL url = new URL(urlstr);  
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
        fetchReqMap(connection);//设置请求属性  
        connection.setReadTimeout(timeout);  
        connection.setDoOutput(false); // true for POST, false for GET  
        connection.setDoInput(true);  
        connection.setRequestMethod("GET");  
        connection.setUseCaches(false);  
          
        String aLine = null;  
        String ret = "";  
        InputStream is = connection.getInputStream();  
        BufferedReader aReader = new BufferedReader(new InputStreamReader(is, this.getRespEncode()));  
          
  
        while ((aLine = aReader.readLine()) != null) {  
            ret += aLine+ "\r\n";;  
        }  
  
  
        aReader.close();  
        connection.disconnect();  
          
        return ret;  
    }  
      
    public String doPost(String urlstr,byte data[]) throws IOException {  
        URL url = new URL(urlstr);  
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
        fetchReqMap(connection);  
//      connection.setRequestProperty("SOAPAction","https://hms.wellcare.cn:8443/services/EnergyData");  
          
        connection.setReadTimeout(timeout);  
        connection.setDoOutput(true); // true for POST, false for GET  
        connection.setDoInput(true);  
        connection.setRequestMethod("POST");  
        connection.setUseCaches(false);  
          
        //写入post数据  
        OutputStream out = connection.getOutputStream();  
        out.write(data);  
          
        //读出反馈结果  
        String aLine = null;  
        String ret = "";  
        InputStream is = connection.getInputStream();  
        BufferedReader aReader = new BufferedReader(new InputStreamReader(is, this.getRespEncode()));  
          
  
        while ((aLine = aReader.readLine()) != null) {  
            ret += aLine + "\r\n";  
        }  
  
  
        aReader.close();  
        connection.disconnect();  
          
        return ret;  
    }  
      
    /** 
     * 直接将文件按二进制写入请求 
     * @param urlstr 
     * @param inputFile 
     * @return 
     * @throws IOException 
     */  
    public String doPost(String urlstr,File inputFile) throws IOException{  
        FileInputStream fis = new FileInputStream(inputFile);  
        byte[] data = new byte[(int) inputFile.length()];  
        fis.read(data);  
        fis.close();  
        return doPost(urlstr, data);  
          
    }  
      
    /** 
     * 字符串俺reqEncode编码方式二级制发送 
     * @param urlstr 
     * @param inputStr 
     * @return 
     * @throws IOException 
     */  
    public String doPost(String urlstr,String inputStr) throws IOException{  
        byte[] data = inputStr.getBytes(this.getReqEncode());  
        return doPost(urlstr, data);  
    }  
      
      
    private void fetchReqMap(HttpsURLConnection connection){  
        Iterator<String> iterator = this.reqProperty.keySet().iterator();  
        while(iterator.hasNext()){  
            String key = iterator.next();  
            connection.setRequestProperty(key, this.reqProperty.get(key));  
        }  
    }  
  
    public void setRespEncode(String respEncode) {  
        this.respEncode = respEncode;  
    }  
  
    public String getRespEncode() {  
        return respEncode;  
    }  
  
    public void setReqEncode(String reqEncode) {  
        this.reqEncode = reqEncode;  
    }  
  
    public String getReqEncode() {  
        return reqEncode;  
    }  
  
}
