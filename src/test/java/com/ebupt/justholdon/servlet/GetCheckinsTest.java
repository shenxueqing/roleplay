package com.ebupt.justholdon.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.ebupt.roleplay.server.north.util.JsonParser;

public class GetCheckinsTest {
	public static void main(String args[]) {
		HttpClient httpClient = new  DefaultHttpClient ();
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/getCheckIns");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid",Config.ouqiuidtest));
		
		params.add(new BasicNameValuePair("password",Config.ouqipwtest));
		params.add(new BasicNameValuePair("startpos","0"));
		params.add(new BasicNameValuePair("type","1"));
//		params.add(new BasicNameValuePair("who","1671130932"));
		params.add(new BasicNameValuePair("habitid","3"));
		params.add(new BasicNameValuePair("num","20"));
		params.add(new BasicNameValuePair("updateway","more"));
	

		
		HttpEntity httpentity;
		
		try {
			httpentity = new UrlEncodedFormEntity(params,"utf8");
			post.setEntity(httpentity);
			String result = httpClient.execute(post,new BasicResponseHandler());
			 System.out.println(result);
			Map<String ,Object> map = (Map<String, Object>) JsonParser.getInstance().jsonToMap(result).get("content");
			List<Map> timelist = (List<Map>) map.get("list");
			for(Map<String,Object> e :timelist){
				String userName = (String) e.get("usename");
//				if(userName.equals("momo")){
					System.out.println(userName);
					System.out.println(e.get("smallpicurl"));
					System.out.println(new Date((Long) e.get("dateline")));
//				}
			
					
			}
		} catch (Exception e) {
			//异常处理，一般记录即可（因为该异常是请求已经重试过若干次后出现的）
			//若有特殊需求可编写单独的异常处理工具类ExceptionHandler进行处理
			//该方法抛出的异常可参见请求重试处理器中处理的各类异常。
		}
		
	}
}
