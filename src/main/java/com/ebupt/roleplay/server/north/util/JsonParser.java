package com.ebupt.roleplay.server.north.util;


import java.util.Map;



import com.ebupt.roleplay.server.north.exception.JhoNorthResultStatus;
import com.ebupt.roleplay.server.north.exception.JhoServerServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParser {
	
	private ObjectMapper mapper = new ObjectMapper();
	private static JsonParser parser ;
	
	public static JsonParser getInstance(){
		if (parser == null){
			parser = new JsonParser();
		}
		return parser;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object>  jsonToMap(String jsonStr) throws JhoServerServiceException{
	
		try {
			 return  mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			JhoServerServiceException ex = new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error,"参数解析失败");
			ex.initCause(e);
			throw ex;
		} 
		
	}
	
	
	public <T> T jsonToJavaBean(String jsonStr,Class<T> entityClass) throws JhoServerServiceException{
		try {
			 return  mapper.readValue(jsonStr, entityClass);
		} catch (Exception e) {
			JhoServerServiceException ex = new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error,"参数解析失败");
			ex.initCause(e);
			throw ex;
		} 
		
	}
	
	public String JavaBeanToJson(Object entity) throws JhoServerServiceException{
		try {
			 return  mapper.writeValueAsString(entity);
		} catch (Exception e) {
			JhoServerServiceException ex = new JhoServerServiceException(JhoNorthResultStatus.reqParser_Error,"参数解析失败");
			ex.initCause(e);
			throw ex;
		} 
		
	}
	
	
	
}
