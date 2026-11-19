package com.demo.identityservice.services;

import com.demo.identityservice.dtos.LoginRequestDto;
import com.demo.identityservice.dtos.RegisterRequestDto;
import com.demo.identityservice.dtos.TokenResponseDto;
import com.demo.identityservice.dtos.UserResponseDto;

public interface IdentityService {
	
	public UserResponseDto register(RegisterRequestDto requestDto);
	
	public TokenResponseDto login(LoginRequestDto requestDto);
}
