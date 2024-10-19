package com.sreenutech.rewards.dao;

import com.sreenutech.rewards.dto.RewardsDaoRequest;
import com.sreenutech.rewards.dto.RewardsDaoResponse;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.SystemException;

public interface IRewardsInfoDao {

	RewardsDaoResponse getRewardsInfo(RewardsDaoRequest rewardsDaoRequest) throws BusinessException, SystemException;

}
