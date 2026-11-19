package com.demo.identityservice.services;

import com.demo.identityservice.dtos.*;
import com.demo.identityservice.models.Customer;
import com.demo.identityservice.models.KeyCloakProperties;
import com.demo.identityservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class IdentityServiceImpl implements IdentityService{
	
	private final KeyCloakAdminService keyCloakAdminService;
	
	private final KeyCloakProperties properties;
	
	private final CustomerRepository customerRepository;
	private final WebClient webClient;
	
	@Override
	public UserResponseDto register(RegisterRequestDto requestDto) {
		
		Customer customer = Customer.builder()
			.userName(requestDto.userName())
			.email(requestDto.email())
			.firstName(requestDto.firstName())
			.lastName(requestDto.lastName())
			.build();
		
		Customer savedCustomer = customerRepository.save(customer);
		
		keyCloakAdminService.createUser(requestDto);
		
		return mapToDto(savedCustomer);
	}
	
	@Override
	public TokenResponseDto login(LoginRequestDto requestDto) {
		
		MultiValueMap<String, String> formData =
			new LinkedMultiValueMap<>();
		
		formData.add("grant_type", "password");
		formData.add("client_id", "spring-microservices");
		formData.add("username", requestDto.userName());
		formData.add("password", requestDto.password());
		
		return webClient.post()
			.uri(properties.getTokenUrl())
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.bodyValue(formData)
			.retrieve()
			.onStatus(
				HttpStatusCode::isError,
				clientResponse -> clientResponse.bodyToMono(String.class)
					.map(RuntimeException::new)
			)
			.bodyToMono(TokenResponseDto.class)
			.block();
	}
	
	private UserResponseDto mapToDto(Customer savedCustomer) {
		
		return new UserResponseDto(
			savedCustomer.getId(),
			savedCustomer.getUserName(),
			savedCustomer.getEmail(),
			savedCustomer.getFirstName(),
			savedCustomer.getLastName()
		);
	}
}
