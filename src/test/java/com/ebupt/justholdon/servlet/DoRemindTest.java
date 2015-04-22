package com.ebupt.justholdon.servlet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class DoRemindTest {
	public static void main(String args[]) {
		HttpClient httpClient = new  DefaultHttpClient ();
		HttpPost post = new HttpPost("http://"+Config.ip+":"+Config.port+"/JustHoldOnServer-1.0.0/doRemind");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid", "1818543793"));
		//params.add(new BasicNameValuePair("habitid","43"));
		params.add(new BasicNameValuePair("password", "13693645329850"));
		params.add(new BasicNameValuePair("who","2048702813"));
		params.add(new BasicNameValuePair("addps","#好习惯需要习以为常# 特别鄙视下@彩虹之西 ~ 当前正在坚持7个习惯,本周还差7个习惯——多练字,多看书,保护眼睛,英语学习,早睡早起,吐槽-习以为常,写周报,未达到目标哟，不要傲娇了快去签到吧~"));


		
		HttpEntity httpentity;
		
		try {
			httpentity = new UrlEncodedFormEntity(params,"utf8");
			post.setEntity(httpentity);
			String result = httpClient.execute(post,new BasicResponseHandler());
			System.out.println(result);
		} catch (Exception e) {
			//异常处理，一般记录即可（因为该异常是请求已经重试过若干次后出现的）
			//若有特殊需求可编写单独的异常处理工具类ExceptionHandler进行处理
			//该方法抛出的异常可参见请求重试处理器中处理的各类异常。
		}
		
	}
}
