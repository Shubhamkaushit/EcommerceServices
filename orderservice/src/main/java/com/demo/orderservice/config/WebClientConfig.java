package com.demo.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Value("${inventory.service.url}")
	private String inventoryServiceUrl;
	
	@Bean
	@LoadBalanced
	public WebClient.Builder webclient(){
		return WebClient.builder().baseUrl(inventoryServiceUrl);
	}
}
