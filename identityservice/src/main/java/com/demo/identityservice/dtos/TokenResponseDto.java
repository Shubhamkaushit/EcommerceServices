package com.demo.identityservice.dtos;

public record TokenResponseDto(
	
	String access_token,
	String refresh_token,
	Integer expires_in,
	Integer refresh_expires_in,
	String token_type,
	String scope
) {
}
