package com.demo.identityservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(

	@NotBlank
	String firstName,
	
	@NotBlank
	String lastName,
	
	@NotBlank
	String userName,
	
	@Email
	String email,
	
	@NotBlank
	String password
) {
}
