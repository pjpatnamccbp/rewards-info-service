package com.sreenutech.rewards.serviceClient;

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

import com.sreenutech.rewards.dto.AuthRequest;
import com.sreenutech.rewards.dto.AuthResponse;
import com.sreenutech.rewards.dto.CardServiceClientResponse;

@Component
public class AuthServiceClientImpl implements IAuthServiceClient{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${auth-service-url}")
	private String authServiceUrl;
	
	
	@Override
	public AuthResponse validateToken(String accessToken) {
		AuthResponse authResponse = new AuthResponse();
		
		
		return authResponse;
	}

	@Override
	public AuthResponse generateToken(AuthRequest authRequest) {
		AuthResponse authResponse = new AuthResponse();
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		headers.add("Authorization","Basic amFnYW4tdXNlcjpqYWdhbi1wYXNz");
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity requestEntity = new HttpEntity<AuthRequest>(authRequest,headers);
		
		ResponseEntity<AuthResponse> responseEntity = restTemplate.exchange(authServiceUrl, HttpMethod.POST,requestEntity,
																	AuthResponse.class);
		
		if(responseEntity != null && responseEntity.getStatusCode().value() == 200) {
			
			authResponse = responseEntity.getBody();
			
		}
		
		return authResponse;
		
		
		/*
		 * try {
		 * 
		 * else { throw new RestClientException("not found"); }
		 * 
		 * } catch (RestClientException e) { throw e; }
		 */
		
		
	}

}
