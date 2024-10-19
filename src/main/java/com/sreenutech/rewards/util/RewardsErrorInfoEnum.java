package com.sreenutech.rewards.util;

import lombok.Getter;

@Getter
public enum RewardsErrorInfoEnum {
	
	clientId_invalid("100", "invalid client_id", "data error"),
	cardnum_invalid("101", "invalid card num", "data error"),
	cardnum_not_found("102", "cardnum not found", "data error"),
	database_timeout("111", "database timedout", "system error"),
	database_down("222", "databse down", "system error"),
	database_unknown("333", "unkown error ", "system error");
	
	private String errorCode;
	private String errorMessage;
	private String typeofError;
	
	private RewardsErrorInfoEnum(String errorCode, String errorMessage, String typeofError) {
		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.typeofError = typeofError;
	}
	
	
	public static boolean checkErrorCode(String errorCode, String typeOfError) {
		
		boolean flag = false;
		
		for( RewardsErrorInfoEnum rewardsEnum : RewardsErrorInfoEnum.values() ) {
			
			if( errorCode.equals(rewardsEnum.getErrorCode()) && typeOfError.equals(rewardsEnum.getTypeofError()) ) {
				
				flag = true;
			
			}
			
		}
		
		return flag;
	}
	
}
