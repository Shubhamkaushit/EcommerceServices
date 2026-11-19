package com.demo.identityservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeyCloakProperties {

	private String serverUrl;
	private String realm;
	private String tokenUrl;
	private String adminTokenUrl;
	private String createUserUrl;
	
	private Admin admin = new Admin();
	
	@Getter
	@Setter
	public static class Admin{
		
		private String username;
		private String password;
		private String clientId;
		private String realm;
	}
}
