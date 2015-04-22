package com.ebupt.justholdon.servlet;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ebupt.roleplay.server.north.util.httpClient.JhoResponseHandler;



     
public class ToWeiboTest {
	
	
	public static void main(String args[]) throws IOException {
		HttpClient httpClient = new  DefaultHttpClient ();
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/toWeibo");
		
		File inFile = new File("D:\\attachments\\1507035727.rounded.png");
		FileInputStream inStream = new FileInputStream(inFile); 
		MultipartEntity multipartEntity = new MultipartEntity();
	    multipartEntity.addPart("uid", new StringBody("1507035727")); 
	    multipartEntity.addPart("password",  new StringBody("13621200181310")); 
	    multipartEntity.addPart("inviteps",  new StringBody("啊呀菠菜菠菜@坚果C","text/plain",Charset.forName("UTF-8"))); 
	  multipartEntity.addPart("type",  new StringBody("1")); 
	   // multipartEntity.addPart("file", new InputStreamBody(inStream,"file")); 
	    
//		HttpPost post = new HttpPost("https://upload.api.weibo.com/2/statuses/upload.json");
//		MultipartEntity multipartEntity = new MultipartEntity();
//	    multipartEntity.addPart("status", new StringBody("strange")); 
//	    multipartEntity.addPart("access_token",  new StringBody("2.00rp3zdBgG2HoDf0d60a86040jE9O9")); 
//	    File file = new File("D:\\attachments\\1507035727.rounded.png");
//	    FileBody inStream = new FileBody(file); 
//	    multipartEntity.addPart("pic", inStream); 				
		
		try {
			
			post.setEntity(multipartEntity);
			String result = httpClient.execute(post,new BasicResponseHandler());
			System.out.println(result);
			
		} catch (Exception e) {
			//异常处理，一般记录即可（因为该异常是请求已经重试过若干次后出现的）
			//若有特殊需求可编写单独的异常处理工具类ExceptionHandler进行处理
			//该方法抛出的异常可参见请求重试处理器中处理的各类异常。
		}
		
	}

	
}
