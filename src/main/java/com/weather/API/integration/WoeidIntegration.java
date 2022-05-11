package com.weather.API.integration;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.weather.API.domain.ResponseCity;

import reactor.core.publisher.Mono;

@Named
public class WoeidIntegration extends Integration{

	@Value("${api.meta.weather.woeid.endpoint}")
	private String endpoint;
	
	public Mono<List<ResponseCity>> findWoeid(final String cityName){
		return webClient.get()
				.uri(endpoint, cityName)
				.retrieve()
				.bodyToFlux(ResponseCity.class)
				.collectList().onErrorMap(e -> {
					System.out.println("NAO FOI POSSIVEL PEGAR O WOEID, ERRO: "+ e.getMessage());
					return e; 
				}).map(obj -> {
					if (obj.size()<1) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WOEID não encontrado!");
					return obj;
				});
	}
}
