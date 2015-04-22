package com.ebupt.roleplay.server.north.util;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueNumGenerator {
	private static UniqueNumGenerator instance = null;
	private AtomicInteger ai=new AtomicInteger(0);
	private static String timestamp = null;
	static{
		timestamp = String.valueOf(System.currentTimeMillis());
	}
	
	private UniqueNumGenerator(){}
	
	public static UniqueNumGenerator getInstance(){
		if(instance == null){
			instance = new UniqueNumGenerator();
		}
		return instance;
	}
	
	public String getUniqueNum(){
		return timestamp+String.valueOf(ai.getAndIncrement());
	}	
}
