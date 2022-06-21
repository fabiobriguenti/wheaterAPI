package com.weather.api.integration;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import reactor.netty.http.client.HttpClient;

@Log4j2
@Setter
public abstract class Integration {
	private String endpoint;
	private Integer timeout;
	
	@Autowired
	private WebClient.Builder builderInjected;
	
	protected WebClient webClient;
	
	@PostConstruct
	public void init() {
		try {
			webClient = builderInjected
				.baseUrl(endpoint)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(Duration.ofMillis(timeout))))
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		} catch (Exception e) {
			log.error("NAO FOI POSSIVEL INICIAR O WEB CLIENT - ", e);
		}
	}
}