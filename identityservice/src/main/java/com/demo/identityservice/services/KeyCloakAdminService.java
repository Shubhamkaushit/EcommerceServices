package com.demo.identityservice.services;

import com.demo.identityservice.dtos.KeyCloakTokenResponse;
import com.demo.identityservice.dtos.KeyCloakUserRequestDto;
import com.demo.identityservice.dtos.RegisterRequestDto;
import com.demo.identityservice.models.Credential;
import com.demo.identityservice.models.KeyCloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyCloakAdminService {

	private final WebClient webClient;
	private final KeyCloakProperties keyCloakProperties;
	
	public KeyCloakTokenResponse getAdminToken(){
		
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		
		formData.add("grant_type", "password");
		formData.add("client_id", keyCloakProperties.getAdmin().getClientId());
		formData.add("username", keyCloakProperties.getAdmin().getUsername());
		formData.add("password", keyCloakProperties.getAdmin().getPassword());
		
		return webClient.post()
			.uri(keyCloakProperties.getAdminTokenUrl())
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.bodyValue(formData)
			.retrieve()
			.bodyToMono(KeyCloakTokenResponse.class)
			.block();
	}
	
	public void createUser(RegisterRequestDto requestDto){
	
		KeyCloakTokenResponse response = getAdminToken();
		
		Credential credential = Credential.builder()
			.type("password")
			.value(requestDto.password())
			.temporary(false)
			.build();
		
		KeyCloakUserRequestDto userRequestDto = KeyCloakUserRequestDto.builder()
			.username(requestDto.userName())
			.email(requestDto.email())
			.firstName(requestDto.firstName())
			.lastName(requestDto.lastName())
			.enabled(true)
			.credentials(List.of(credential))
			.build();
		
		webClient.post().uri(keyCloakProperties.getCreateUserUrl())
			.header(
				"Authorization",
				"Bearer "+response.access_token()
			)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(userRequestDto)
			.retrieve()
			.toBodilessEntity()
			.block();
	}
}
