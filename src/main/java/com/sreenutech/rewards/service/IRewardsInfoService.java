package com.sreenutech.rewards.service;

import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.SystemException;

public interface IRewardsInfoService {

	RewardsResponse getRewardsInfo(RewardsRequest rewardsRequest) throws BusinessException, SystemException;

}
