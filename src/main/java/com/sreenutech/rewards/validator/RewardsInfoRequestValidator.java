package com.sreenutech.rewards.validator;

import org.springframework.stereotype.Component;

import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.exception.RewardsInfoRequestInvalidException;

@Component
public class RewardsInfoRequestValidator {

	public void validateRequest(RewardsRequest rewardsRequest) throws RewardsInfoRequestInvalidException {
		
		if( rewardsRequest.getCardNum() == null || " ".equals(rewardsRequest.getCardNum()) || rewardsRequest.getCardNum().length() < 16 ) {
			throw new RewardsInfoRequestInvalidException("rwds001", "card number invalid");
		}
		
		if( rewardsRequest.getClientId() == null || " ".equals(rewardsRequest.getClientId())) {
			throw new RewardsInfoRequestInvalidException("rwds002", "client id invalid");
		}
		
		if( rewardsRequest.getRequestId() == null || " ".equals(rewardsRequest.getRequestId())) {
			throw new RewardsInfoRequestInvalidException("rwds003", "request id invalid");
		}
		
		if( rewardsRequest.getMessageTS() == null || " ".equals(rewardsRequest.getMessageTS())) {
			throw new RewardsInfoRequestInvalidException("rwds004", "message TS invalid");
		}
		
	}

}
