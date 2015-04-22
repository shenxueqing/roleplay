package com.ebupt.justholdon.servlet;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ebupt.roleplay.server.north.util.httpClient.JhoResponseHandler;



     
public class AddBookTest {
	
	
	public static void main(String args[]) throws IOException {
		HttpClient httpClient = new  DefaultHttpClient ();
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/addBook");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid",Config.ouqiuidtest));
		params.add(new BasicNameValuePair("password",Config.ouqipwtest));
		params.add(new BasicNameValuePair("bid","3"));	
		
		try {
			
			post.setEntity(new UrlEncodedFormEntity(params,"utf8"));
			String result = httpClient.execute(post,new BasicResponseHandler());
			System.out.println(result);
			
		} catch (Exception e) {
			//异常处理，一般记录即可（因为该异常是请求已经重试过若干次后出现的）
			//若有特殊需求可编写单独的异常处理工具类ExceptionHandler进行处理
			//该方法抛出的异常可参见请求重试处理器中处理的各类异常。
		}
		
	}

	
}
