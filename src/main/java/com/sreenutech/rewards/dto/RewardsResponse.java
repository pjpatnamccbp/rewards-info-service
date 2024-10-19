package com.sreenutech.rewards.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RewardsResponse {
	
	private StatusBlock statusBlock;
	private List<Transactions> transactionsList;

}
