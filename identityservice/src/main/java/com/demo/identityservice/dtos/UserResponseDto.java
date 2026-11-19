package com.demo.identityservice.dtos;

public record UserResponseDto(
	
	Long id,

	String userName,
	
	String email,
	
	String firstName,
	
	String lastName
) {
}
