package com.sreenutech.rewards.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.dto.RewardsResponse;
import com.sreenutech.rewards.dto.StatusBlock;
import com.sreenutech.rewards.dto.Transactions;
import com.sreenutech.rewards.exception.BusinessException;
import com.sreenutech.rewards.exception.RewardsInfoRequestInvalidException;
import com.sreenutech.rewards.exception.SystemException;
import com.sreenutech.rewards.service.IRewardsInfoService;
import com.sreenutech.rewards.validator.RewardsInfoRequestValidator;


@RunWith(PowerMockRunner.class)
@PrepareForTest({RewardsInfoRequestValidator.class, IRewardsInfoService.class})
public class RewardsInfoControllerTest {

	RewardsInfoController rewardsInfoController;
	
	RewardsInfoRequestValidator mockRequestValidator;
	
	IRewardsInfoService mockiRewardsInfoService;
	
	@Before
	public void setUp() throws Exception {
		
		rewardsInfoController = new RewardsInfoController();
		mockRequestValidator = PowerMockito.mock(RewardsInfoRequestValidator.class);
		mockiRewardsInfoService = PowerMockito.mock(IRewardsInfoService.class);
		
		Whitebox.setInternalState(rewardsInfoController, "rewardsRequestValidator", mockRequestValidator);
		Whitebox.setInternalState(rewardsInfoController, "iRewardsInfoService", mockiRewardsInfoService);
	}

	
	@Test
	public void testGetRewardsInfo_success_scenario() throws RewardsInfoRequestInvalidException, BusinessException, SystemException {
		//stubbing
		
		PowerMockito.doNothing().when(mockRequestValidator).validateRequest(Matchers.any(RewardsRequest.class));
		//PowerMockito.doCallRealMethod().when(mockRequestValidator).validateRequest(Matchers.any(RewardsRequest.class));
		
		PowerMockito.when(mockiRewardsInfoService.getRewardsInfo(Matchers.any(RewardsRequest.class))).thenReturn(buildMockResponse());
		
		
		//expectation
		RewardsResponse rewardsResponse = rewardsInfoController.getRewardsInfo("7612264802100210", "web", "", "abc123xyz", "22/08/2024");
		
		
		//verify
		assertNotNull(rewardsResponse);
		
		assertEquals("0", rewardsResponse.getStatusBlock().getResponseCode());
		
	}
	

	private RewardsResponse buildMockResponse() {
		System.out.println("------enterd into buildMockResponse method-----");
		
		RewardsResponse rewardsResponse = new RewardsResponse();
		
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setResponseCode("0");
		statusBlock.setResponseMessage("success");
		
		List<Transactions>  transactionsList = new ArrayList<Transactions>();
		
		rewardsResponse.setStatusBlock(statusBlock);
		rewardsResponse.setTransactionsList(transactionsList);
		
		System.out.println("------exited from buildMockResponse method------");
		
		return rewardsResponse;
	}


	@After
	public void tearDown() throws Exception {
	}

	

}
