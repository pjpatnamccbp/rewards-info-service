package com.sreenutech.rewards.dto;

import lombok.Data;

@Data
public class RewardsRequest {
	
	private String cardNum;
	
	private String clientId;
	
	private String requestId;
	
	private String messageTS;
	
	private String qwerty;
}
