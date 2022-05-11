package com.weather.API.integration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Named
public abstract class Integration {

	@Value("${api.meta.weather.endpoint}")
	private String endpoint;
	
	@Inject
	private WebClient.Builder builderInjected;
	
	protected WebClient webClient;
	
	@PostConstruct
	public void init() {
		try {
			final HttpClient httpClient = HttpClient.create();
			final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
			
			webClient = builderInjected
						.baseUrl(endpoint)
						.clientConnector(connector)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
						.build();
			
		} catch (Exception e) {
			System.out.println("NAO FOI POSSIVEL INICIAR O WEB CLIENT");
			System.out.println(e.getMessage());
		}
	}
}
