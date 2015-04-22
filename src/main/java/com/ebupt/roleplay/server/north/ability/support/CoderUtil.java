package com.ebupt.roleplay.server.north.ability.support;

import java.io.IOException;


public class CoderUtil {
	public static String  urlDecoder(String s) throws IOException{
		s = java.net.URLDecoder.decode(s, "UTF-8");
		s = java.net.URLDecoder.decode(s, "UTF-8");
		return s;
	}
}
