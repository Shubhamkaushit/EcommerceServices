package com.demo.identityservice;

import com.demo.identityservice.models.KeyCloakProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StartupRunner implements CommandLineRunner {
	
	private final KeyCloakProperties properties;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Server URL: " + properties.getServerUrl());
		System.out.println("Realm: " + properties.getRealm());
		
		System.out.println("Admin Username: "
			+ properties.getAdmin().getUsername());
		
		System.out.println("Admin Client ID: "
			+ properties.getAdmin().getClientId());
	}
}
