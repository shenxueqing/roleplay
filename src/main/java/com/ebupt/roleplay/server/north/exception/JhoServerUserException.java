package com.ebupt.roleplay.server.north.exception;

@SuppressWarnings("serial")
public class JhoServerUserException extends JhoServerException {

	public JhoServerUserException(String errorcode, String errorText) {
		super("user"+errorcode, errorText);
	}
	
		
		
}
