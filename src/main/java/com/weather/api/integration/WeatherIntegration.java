package com.weather.api.integration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.weather.api.domain.ResponseWoeid;

import io.netty.handler.timeout.TimeoutException;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@ConfigurationProperties("api.meta.weather")
public class WeatherIntegration extends Integration {
	
	public Mono<ResponseWoeid> findFullWeather(Integer woeid){
		return webClient.get()
			.uri(uri -> uri.build(woeid))
			.retrieve()
			.bodyToMono(ResponseWoeid.class)
			.doOnError(e -> log.error("NAO FOI POSSIVEL PEGAR O FULL WEATHER, ERRO: "+ e))
			.onErrorReturn(WebClientResponseException.NotFound.class, null)
			.onErrorResume(e -> e.getCause() instanceof TimeoutException, e -> Mono.error(new RuntimeException("TimeoutException ao chamar o FullWeather")));
	}
}