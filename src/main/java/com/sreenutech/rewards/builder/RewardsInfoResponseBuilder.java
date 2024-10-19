package com.sreenutech.rewards.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sreenutech.rewards.dto.CardServiceClientResponse;
import com.sreenutech.rewards.dto.RewardsDaoResponse;
import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.dto.StatusBlock;
import com.sreenutech.rewards.dto.Transactions;
import com.sreenutech.rewards.dto.TransactionsDao;

@Component
public class RewardsInfoResponseBuilder {

	public RewardsResponse buildRewardsResponse(CardServiceClientResponse cardServiceClientResponse,
			RewardsDaoResponse rewardsDaoResponse) {
		// TODO Auto-generated method stub
		
		RewardsResponse rewardsResponse = new RewardsResponse();
		
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setResponseCode(cardServiceClientResponse.getResponseCode());
		statusBlock.setResponseMessage(cardServiceClientResponse.getResponseMessage());
		
		List<Transactions> transactionsList = new ArrayList<Transactions>();
		
		for(TransactionsDao txnDao : rewardsDaoResponse.getTransactionsDao()) {
			
			Transactions transactions = new Transactions();
			transactions.setTxnId(txnDao.getTxnId());
			transactions.setName(txnDao.getName());
			transactions.setMerchantName(txnDao.getMerchantName());
			transactions.setAmount(txnDao.getAmount());
			transactions.setDate(txnDao.getDate());
			transactions.setDescription(txnDao.getDescription());
			transactions.setPoints(txnDao.getPoints());
			transactions.setRemarks(txnDao.getRemarks());
			transactions.setStatus(txnDao.getStatus());
			
			transactionsList.add(transactions);
		}
		
		rewardsResponse.setStatusBlock(statusBlock);
		rewardsResponse.setTransactionsList(transactionsList);
		
		return rewardsResponse;
	}

}
