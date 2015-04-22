package com.ebupt.roleplay.server.north.util.httpClient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebupt.roleplay.server.north.exception.JhoNorthResultStatus;
import com.ebupt.roleplay.server.north.exception.JhoServerServiceException;
import com.ebupt.roleplay.server.north.exception.JhoSocialException;
import com.ebupt.roleplay.server.north.util.JsonParser;

public class JhoResponseHandler implements ResponseHandler<String>{
	private static Logger logger = LoggerFactory.getLogger(JhoResponseHandler.class);
	 public String handleResponse(final HttpResponse response)
     throws HttpResponseException, IOException {
		 StatusLine statusLine = response.getStatusLine();
		 HttpEntity entity = response.getEntity();
		 if (statusLine.getStatusCode() >= 300) {
			 String errorMsg = (entity == null ? null : EntityUtils.toString(entity));
			 logger.debug("Result:{}",errorMsg);
			 EntityUtils.consume(entity);
			 Map<String, Object> map = null;
			try {
				map = JsonParser.getInstance().jsonToMap(errorMsg);
			} catch (JhoServerServiceException e) {
				 throw new JhoSocialException(Integer.valueOf(JhoNorthResultStatus.Social_resp_Error),"新浪微博结果解析失败");	
			}
			 if(map.get("error_code")!= null&& map.get("error_code").equals(20012) &&!map.get("error_code").equals("20012")){
				 throw new JhoSocialException((Integer)(map.get("error_code")),"新浪微博错误："+map.get("error"));	
			 }
			return errorMsg;
		 }
		 return entity == null ? null : EntityUtils.toString(entity);
	 }
	}
