package com.weather.API.integration;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import com.weather.API.domain.ResponseWoeid;

import reactor.core.publisher.Mono;

@Named
public class FullWeatherIntegration extends Integration{

	@Value("${api.meta.weather.full.weather.endpoint}")
	private String endpoint;
	
	public Mono<ResponseWoeid> findFullWeather(Integer woeid){
		return webClient.get()
			.uri(endpoint, woeid)
			.retrieve()
			.bodyToMono(ResponseWoeid.class)
			.onErrorMap(e -> {
				System.out.println("NAO FOI POSSIVEL PEGAR O FULL WEATHER, ERRO: "+ e.getMessage());
				return e; 
			});
	}
}
