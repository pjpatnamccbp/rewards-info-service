package com.sreenutech.rewards.serviceClient;

import com.sreenutech.rewards.dto.AuthRequest;
import com.sreenutech.rewards.dto.AuthResponse;

public interface IAuthServiceClient {
	
	public AuthResponse validateToken(String accessToken);
	
	public AuthResponse generateToken(AuthRequest authRequest);
	
}
