package com.ebupt.justholdon.servlet;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;



import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class ModifyAvatarTest {

	public static void main(String args[]) throws IOException {
		HttpClient httpClient = new  DefaultHttpClient ();
	
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/modifyAvatar");
		File inFile = new File("D:\\attachments\\1762687133_rounded.png");
		FileInputStream inStream = new FileInputStream(inFile); 
		MultipartEntity multipartEntity = new MultipartEntity();
	    multipartEntity.addPart("uid", new StringBody("3069460223")); 
	    multipartEntity.addPart("password",  new StringBody("13637420814513")); 
	    multipartEntity.addPart("file", new InputStreamBody(inStream,"file")); 

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
