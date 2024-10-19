package com.sreenutech.rewards.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.dto.StatusBlock;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.RewardsInfoRequestInvalidException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.util.RewardsInfoConstants;

@ControllerAdvice
public class RewardsInfoControllerAdvice {
	
	@ExceptionHandler(RewardsInfoRequestInvalidException.class)
	@ResponseBody
	public RewardsResponse handleRewardsInfoRequestInvalidException(RewardsInfoRequestInvalidException exception) {
		
		return buildRewardsResponse(exception.getErrorCode(),exception.getErrorMessage());
	}
	
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public RewardsResponse handleBusinessException(BusinessException exception) {
		
		return buildRewardsResponse(exception.getResponseCode(), exception.getResponseMessage());
	}

	
	@ExceptionHandler(SystemException.class)
	@ResponseBody
	public RewardsResponse handleDataError(SystemException exception) {
		
		return buildRewardsResponse(exception.getResponseCode(), exception.getResponseMessage());
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public RewardsResponse handleGenericErrors(Exception exception) {
		
		return buildRewardsResponse(RewardsInfoConstants.UNKOWN_ERROR_CODE, RewardsInfoConstants.UNKOWN_ERROR_MESSAGE);
	}
	
	@ExceptionHandler(RestClientException.class)
	@ResponseBody
	public RewardsResponse handleRestClientException(RestClientException exception){
	
		return buildRewardsResponse(RewardsInfoConstants.SERVICE_CLIENT_ERROR_CODE, RewardsInfoConstants.SERVICE_CLIENT_ERROR_CODE);
	}
	
	
	private RewardsResponse buildRewardsResponse(String responseCode, String responseMessage) {
		RewardsResponse response = new RewardsResponse();
		
		StatusBlock statusBlock = new StatusBlock(); 
		statusBlock.setResponseCode(responseCode);
		statusBlock.setResponseMessage(responseMessage);
		
		response.setStatusBlock(statusBlock);
		return response;
	}
}
