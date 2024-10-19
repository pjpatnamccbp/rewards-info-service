 package com.sreenutech.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.RewardsInfoRequestInvalidException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.service.IRewardsInfoService;
import com.sreenutech.rewards.util.RewardsInfoConstants;
import com.sreenutech.rewards.validator.RewardsInfoRequestValidator;

@RestController
public class RewardsInfoController {
	
	@Autowired
	private IRewardsInfoService iRewardsInfoService;
	
	@Autowired
	private RewardsInfoRequestValidator rewardsRequestValidator;

	@GetMapping("/rewards/{cardnum}")
	public RewardsResponse getRewardsInfo(@PathVariable(RewardsInfoConstants.CARDNUM) String cardnum,
										  @RequestHeader(RewardsInfoConstants.ACCESS_TOKEN) String accessToken,
										  @RequestHeader(RewardsInfoConstants.CLIENT_ID) String clientId,
										  @RequestHeader(RewardsInfoConstants.REQUEST_ID) String requestId,
										  @RequestHeader(RewardsInfoConstants.MESSAGE_TS) String messageTS) throws RewardsInfoRequestInvalidException, BusinessException, SystemException {
		
		RewardsRequest rewardsRequest = new RewardsRequest(); 
		
		rewardsRequest.setCardNum(cardnum);
		//" ".equals(rewardsRequest.getCardNum())
		rewardsRequest.setClientId(clientId);
		rewardsRequest.setRequestId(requestId);
		rewardsRequest.setMessageTS(messageTS);
		
		//validate request 
		rewardsRequestValidator.validateRequest(rewardsRequest);
		
		RewardsResponse rewardsResponse = iRewardsInfoService.getRewardsInfo(rewardsRequest);
		
		return rewardsResponse;
		
		
	}
	
}
