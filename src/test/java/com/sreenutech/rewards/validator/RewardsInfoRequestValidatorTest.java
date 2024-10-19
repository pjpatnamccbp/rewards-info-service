/**
 * 
 */
package com.sreenutech.rewards.validator;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sreenutech.rewards.dto.RewardsRequest;
import com.sreenutech.rewards.exception.RewardsInfoRequestInvalidException;

/**
 * @author hp
 *
 */
public class RewardsInfoRequestValidatorTest {
	
	RewardsInfoRequestValidator  rewardsInfoRequestValidator;
	RewardsRequest rewardsRequest;
	
	@Before
	public void setUp() throws Exception {
		 rewardsInfoRequestValidator = new RewardsInfoRequestValidator();
		 rewardsRequest = buildRewardsRequest();
	}

	
	private RewardsRequest buildRewardsRequest() {
		
		RewardsRequest rewardsRequest = new RewardsRequest();
		
		rewardsRequest.setCardNum("1234567887654321");
		rewardsRequest.setClientId("web");
		rewardsRequest.setRequestId(UUID.randomUUID().toString());
		rewardsRequest.setMessageTS(new Date().toString());
		
		
		
		return rewardsRequest;
	}


	@Test
	public void test_cardnum_null_scenario() {
		
		rewardsRequest.setCardNum(null);
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds001", e.getErrorCode());
			assertEquals("card number invalid", e.getErrorMessage());
		}
		
		
	}
	
	@Test
	public void test_cardnum_length_scenario() {
		
		rewardsRequest.setCardNum("123456788765432");
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds001", e.getErrorCode());
			assertEquals("card number invalid", e.getErrorMessage());
		}
		
		
	}
	
	@Test
	public void test_cardnum_empty_scenario() {
		
		rewardsRequest.setCardNum(" ");
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds001", e.getErrorCode());
			assertEquals("card number invalid", e.getErrorMessage());
		}
	}
	
	
	@Test
	public void test_clientId_null_scenario() {
		rewardsRequest.setClientId(null);
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds002", e.getErrorCode());
			assertEquals("client id invalid", e.getErrorMessage());
		}
	}
	
	@Test
	public void test_clientId_empty_scenario() {
		rewardsRequest.setClientId(" ");
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds002", e.getErrorCode());
			assertEquals("client id invalid", e.getErrorMessage());
		}
		
	}
	
	@Test
	public void test_requestId_null_scenario() {
		rewardsRequest.setRequestId(null);
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds003", e.getErrorCode());
			assertEquals("request id invalid", e.getErrorMessage());
		}
		
	}
	
	@Test
	public void test_requestId_empty_scenario() {
		rewardsRequest.setRequestId(" ");
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds003", e.getErrorCode());
			assertEquals("request id invalid", e.getErrorMessage());
		}
		
	}
	
	@Test
	public void test_messageTS_empty_scenario() {
		rewardsRequest.setMessageTS(" ");
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds004", e.getErrorCode());
			assertEquals("message TS invalid", e.getErrorMessage());
		}
		
	}
	
	@Test
	public void test_messageTS_null_scenario() {
		rewardsRequest.setMessageTS(null);
		
		try {
		
			rewardsInfoRequestValidator.validateRequest(rewardsRequest);
		
		} catch (RewardsInfoRequestInvalidException e) {
			
			assertEquals("rwds004", e.getErrorCode());
			assertEquals("message TS invalid", e.getErrorMessage());
		}
		
	}
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		rewardsInfoRequestValidator = null;
	}


}
