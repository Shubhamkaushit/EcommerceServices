package com.demo.identityservice.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
	
	@NotBlank
	String userName,
	
	@NotBlank
	String password
) {
}
