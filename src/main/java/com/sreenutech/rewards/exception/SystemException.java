package com.sreenutech.rewards.exception;

import lombok.Getter;

@Getter
public class SystemException extends Exception{
	
	private static final long serialVersionUID = -4059799890843942555L;
	
	private String responseCode;
	private String responseMessage;
	
	public SystemException(String responseCode, String responseMessage) {
		
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	
}
