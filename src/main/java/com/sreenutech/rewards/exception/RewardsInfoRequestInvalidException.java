package com.sreenutech.rewards.exception;

import lombok.Getter;

@Getter
public class RewardsInfoRequestInvalidException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5649354770435284012L;
	private String errorCode;
	private String errorMessage;
	
	public RewardsInfoRequestInvalidException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
