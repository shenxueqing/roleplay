package com.ebupt.roleplay.server.north.exception;

import org.apache.http.client.HttpResponseException;

public class JhoSocialException extends HttpResponseException{
	public JhoSocialException(int statusCode, String s) {
		super(statusCode, s);
		// TODO Auto-generated constructor stub
	}
}
