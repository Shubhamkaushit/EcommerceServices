package com.demo.identityservice.controllers;

import com.demo.identityservice.dtos.LoginRequestDto;
import com.demo.identityservice.dtos.RegisterRequestDto;
import com.demo.identityservice.dtos.TokenResponseDto;
import com.demo.identityservice.dtos.UserResponseDto;
import com.demo.identityservice.services.IdentityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identity")
@AllArgsConstructor
public class IdentityController {
	
	private final IdentityService identityService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterRequestDto requestDto){
	
		return ResponseEntity.status(HttpStatus.CREATED).body(identityService.register(requestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto){
		
		return ResponseEntity.status(HttpStatus.OK).body(identityService.login(requestDto));
	}
}
