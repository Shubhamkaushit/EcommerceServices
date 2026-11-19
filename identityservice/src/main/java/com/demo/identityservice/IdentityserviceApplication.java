package com.demo.identityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class IdentityserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IdentityserviceApplication.class, args);
	}
	
}
