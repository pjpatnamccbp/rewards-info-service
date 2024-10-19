package com.sreenutech.rewards.dto;

import lombok.Data;

@Data
public class TransactionsDao {
	private String txnId;
	private String name;
	private String date;
	private float amount;
	private String merchantName;
	private String description;
	private String status;
	private long points;
	private String remarks;
	
}
