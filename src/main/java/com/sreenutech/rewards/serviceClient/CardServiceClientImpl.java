package com.sreenutech.rewards.serviceClient;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sreenutech.rewards.dto.CardServiceClientRequest;
import com.sreenutech.rewards.dto.CardServiceClientResponse;

@Component
public class CardServiceClientImpl implements ICardServiceClient{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${card-details-service-url}")
	private String cardDetailServiceUrl;
	
	@Override
	public CardServiceClientResponse getCardInfo(CardServiceClientRequest cardServiceClientRequest) throws RestClientException {
		
		CardServiceClientResponse cardServiceClientResponse = new CardServiceClientResponse();
		
		/*
		 * cardServiceClientResponse.setResponseCode("0");
		 * cardServiceClientResponse.setResponseMessage("success");
		 * cardServiceClientResponse.setProductCode(null);
		 * cardServiceClientResponse.setProductType(null);
		 * cardServiceClientResponse.setStatus("");
		 */
		
		String serviceUrl = cardDetailServiceUrl + cardServiceClientRequest.getCardnum();
		
		try {
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			
			headers.add("X_CLIENT_ID", "web");
			headers.add("X_REQUEST_ID", "abc123xyz");
			headers.add("X_MSG_TS", new Date().toString());
			
			HttpEntity requestEntity = new HttpEntity<>(headers);
			
			ResponseEntity<CardServiceClientResponse> responseEntity 
						= restTemplate.exchange(serviceUrl, HttpMethod.GET, requestEntity,CardServiceClientResponse.class);
			
			if(responseEntity != null && responseEntity.getStatusCode().value() == 200) {
				
				cardServiceClientResponse = responseEntity.getBody();
				
			}else {
				throw new RestClientException(responseEntity.getStatusCode().toString());
			}
			
		} catch (RestClientException e) {
			throw e;
		}
		
		
		return cardServiceClientResponse;
	}

}
