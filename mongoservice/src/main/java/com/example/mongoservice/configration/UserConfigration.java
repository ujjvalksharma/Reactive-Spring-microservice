package com.example.mongoservice.configration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserConfigration {

	 @Bean
	 @LoadBalanced
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	 
	 
	 @Bean
	 @LoadBalanced
	 public WebClient.Builder getWebClientBuilder() {
		 return WebClient.builder();
	 }
}
