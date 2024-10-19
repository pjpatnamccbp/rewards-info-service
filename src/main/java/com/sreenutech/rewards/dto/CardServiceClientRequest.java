package com.sreenutech.rewards.dto;

import lombok.Data;

@Data
public class CardServiceClientRequest {
	
	private String cardnum;
	
	private String requestId;
	
	private String clientId;
	
	private String messageTs;
}
