package com.sreenutech.rewards.builder;

import org.springframework.stereotype.Component;

import com.sreenutech.rewards.dto.CardServiceClientRequest;
import com.sreenutech.rewards.dto.RewardsDaoRequest;
import com.sreenutech.rewards.dto.RewardsRequest;

@Component
public class RewardsInfoRequestBuilder {

	public CardServiceClientRequest buildServiceClientRequest(RewardsRequest rewardsRequest) {
		// TODO Auto-generated method stub
		
		CardServiceClientRequest cardServiceClientRequest = new CardServiceClientRequest();
		//" ".equals(rewardsRequest.getCardNum())
		
		cardServiceClientRequest.setCardnum(rewardsRequest.getCardNum());
		cardServiceClientRequest.setRequestId(rewardsRequest.getRequestId());
		cardServiceClientRequest.setClientId( rewardsRequest.getClientId());
		cardServiceClientRequest.setMessageTs(rewardsRequest.getMessageTS());
		
		return cardServiceClientRequest;
	}

	public RewardsDaoRequest buildDaoRequest(RewardsRequest rewardsRequest) {
		// TODO Auto-generated method stub
		
		RewardsDaoRequest rewardsDaoRequest = new RewardsDaoRequest();
		
		rewardsDaoRequest.setCardNum(rewardsRequest.getCardNum());
		rewardsDaoRequest.setClientId(rewardsRequest.getClientId());
		
		return rewardsDaoRequest;
	}

}
