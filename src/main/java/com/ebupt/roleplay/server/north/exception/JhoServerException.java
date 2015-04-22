package com.ebupt.roleplay.server.north.exception;



@SuppressWarnings("serial")
public class JhoServerException extends Exception {
	private String errorCode;
	
	
	
	public JhoServerException(String errorcode,String message) {
		super(message);
		this.errorCode = errorcode;
		
	}
	

	
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorText() {
		return super.getMessage();
	}

	
}

