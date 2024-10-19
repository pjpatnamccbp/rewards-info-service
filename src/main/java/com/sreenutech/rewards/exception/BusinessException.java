package com.sreenutech.rewards.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4168154586919908721L;
	private String responseCode;
	private String responseMessage;
	
	public BusinessException(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	
}
