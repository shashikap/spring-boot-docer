package com.randoli.event.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

	@Value("${api.event.url}")
	String eventApiUrl;
	
	@Bean
	public WebClient getWebClient() {
		return WebClient.create(eventApiUrl);
	}
}
