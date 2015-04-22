package com.ebupt.justholdon.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class AccessFriendTest {
	public static void main(String args[]) {
		HttpClient httpClient = new  DefaultHttpClient ();
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/accessFriend");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid",Config.uid));
		params.add(new BasicNameValuePair("password",Config.pw));
		params.add(new BasicNameValuePair("accessuid",Config.ouqiuid));
		
		
			
		
				String path = "D:\\attachments\\ff";
				File[] files = new File(path).listFiles();
				for(File file: files){
					File[] aFile = file.listFiles();
					for(File sfile:aFile){	
						try {
						Thumbnails.of(sfile.listFiles())
				        .scale(0.5f)
				        .outputFormat("jpg")
				        .toFiles(new Rename() {
				    		@Override
				    		public String apply(String fileName, ThumbnailParameter param)
				    		{
				    			return  "small";
				    		}

							
				    	});
						} catch (IOException e) {
							
						} 
					
				}
			} 
				
				
			
		
//
//		HttpEntity httpentity;
//		
//		try {
//			httpentity = new UrlEncodedFormEntity(params,"utf8");
//			post.setEntity(httpentity);
//			String result = httpClient.execute(post,new BasicResponseHandler());
//			System.out.println(result);
//		} catch (Exception e) {
//			//异常处理，一般记录即可（因为该异常是请求已经重试过若干次后出现的）
//			//若有特殊需求可编写单独的异常处理工具类ExceptionHandler进行处理
//			//该方法抛出的异常可参见请求重试处理器中处理的各类异常。
//		}
	}
}
