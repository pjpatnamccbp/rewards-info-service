package com.sreenutech.rewards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sreenutech.rewards.builder.RewardsInfoRequestBuilder;
import com.sreenutech.rewards.builder.RewardsInfoResponseBuilder;
import com.sreenutech.rewards.dao.IRewardsInfoDao;
import com.sreenutech.rewards.dto.CardServiceClientRequest;
import com.sreenutech.rewards.dto.CardServiceClientResponse;
import com.sreenutech.rewards.dto.RewardsDaoRequest;
import com.sreenutech.rewards.dto.RewardsDaoResponse;
import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.serviceClient.ICardServiceClient;

@Service	
public class RewardsInfoServiceImpl implements IRewardsInfoService {
	
	@Autowired
	private ICardServiceClient icardServiceClient;
	
	@Autowired
	private IRewardsInfoDao irewardsInfoDao;
	
	@Autowired
	private RewardsInfoRequestBuilder rewardsInfoRequestBuilder;
		
	@Autowired
	private RewardsInfoResponseBuilder rewardsInfoResponseBuilder;
	
	
	@Override
	public RewardsResponse getRewardsInfo(RewardsRequest rewardsRequest) throws BusinessException, SystemException {
		
		CardServiceClientRequest cardServiceClientRequest = rewardsInfoRequestBuilder.buildServiceClientRequest(rewardsRequest);
				
		CardServiceClientResponse cardServiceClientResponse = icardServiceClient.getCardInfo(cardServiceClientRequest);
		
		RewardsDaoRequest rewardsDaoRequest = rewardsInfoRequestBuilder.buildDaoRequest(rewardsRequest);
		
		RewardsDaoResponse rewardsDaoResponse = irewardsInfoDao.getRewardsInfo(rewardsDaoRequest);
				
		RewardsResponse rewardsResponse = rewardsInfoResponseBuilder.buildRewardsResponse(cardServiceClientResponse, rewardsDaoResponse);
		
		
		return rewardsResponse;
	}



}
