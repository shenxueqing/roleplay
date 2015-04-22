package com.ebupt.roleplay.server.north.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



public class JhoStringBuider {
	public static String buildCommaStr(Set<String> StrSet){
		return buildStr(StrSet.iterator(),",");
	}


	public static String buildCommaStr(List<String> StrSet){
		return buildStr(StrSet.iterator(),",");
		
	}
	
	public static String buildStr(Iterator<String> it,String separator){
		StringBuilder builder = new StringBuilder();
		if(it.hasNext()){
			builder.append(it.next());
		}else{
			return null;
		}
		while(it.hasNext()){
			builder.append(separator);
			builder.append(it.next());
		}
		return builder.toString();
	}

	public static String buildWeiboStr(String[] names){
		StringBuilder sb = new StringBuilder();
		
		for(String name :names){
			sb.append("@");
			sb.append(name);
		}
		return sb.toString();
	}
	
	public   static   String   inputStream2String(InputStream   is)   throws   IOException{ 
        ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
        int   i=-1; 
        while((i=is.read())!=-1){ 
        baos.write(i); 
        } 
       return   baos.toString("utf-8"); 
}
}
