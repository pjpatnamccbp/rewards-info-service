package com.sreenutech.rewards.dto;

import lombok.Data;

@Data
public class CardServiceClientResponse {
	
	private String responseCode;
	private String responseMessage;
	private String status;
	private String productType;
	private String productCode;
	private String remarks;
}
