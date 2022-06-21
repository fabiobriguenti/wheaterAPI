package com.weather.api.integration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.weather.api.domain.ResponseCity;

import io.netty.handler.timeout.TimeoutException;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@ConfigurationProperties("api.meta.woeid")
public class WoeidIntegration extends Integration {
	
	public Flux<ResponseCity> findWoeid(String cityName) {
		return webClient.get()
			.uri(uri -> uri.build(cityName))
			.retrieve()
			.bodyToFlux(ResponseCity.class)
			.doOnError(e -> log.error("NAO FOI POSSIVEL PEGAR O WOEID, ERRO: "+ e))
			.onErrorReturn(WebClientResponseException.NotFound.class, null)
			.onErrorResume(e -> e.getCause() instanceof TimeoutException, e -> Mono.error(new RuntimeException("TimeoutException ao chamar o Woeid")));
	}
}