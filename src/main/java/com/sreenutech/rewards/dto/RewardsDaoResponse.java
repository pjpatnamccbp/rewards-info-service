package com.sreenutech.rewards.dto;

import java.util.List;

import lombok.Data;

@Data
public class RewardsDaoResponse {
	private String responseCode;
	private String responseMessage;
	
	private List<TransactionsDao> transactionsDao;
}
