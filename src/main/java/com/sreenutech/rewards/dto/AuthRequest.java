package com.sreenutech.rewards.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String username;
	private String password;
	private String grantType;
	
	
}
