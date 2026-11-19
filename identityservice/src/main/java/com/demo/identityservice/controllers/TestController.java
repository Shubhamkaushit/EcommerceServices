package com.demo.identityservice.controllers;

import com.demo.identityservice.dtos.KeyCloakTokenResponse;
import com.demo.identityservice.dtos.RegisterRequestDto;
import com.demo.identityservice.services.KeyCloakAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
	
	private final KeyCloakAdminService keyCloakAdminService;
	
	@GetMapping("admin-token")
	public KeyCloakTokenResponse getAdminToken() {
		return keyCloakAdminService.getAdminToken();
	}
	
	@PostMapping("/user")
	public String createUser() {
		
		RegisterRequestDto request =
			new RegisterRequestDto(
				"Shubham",
				"Kumar",
				"shubham",
				"shubham@gmail.com",
				"password123"
			);
		
		keyCloakAdminService.createUser(request);
		
		return "User Created";
	}
}
