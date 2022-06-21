package com.weather.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.weather.api.domain.City;
import com.weather.api.service.CityService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CityController.class)
class CityControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private CityService cityService;

	@Test
	@DisplayName("Quando o service retornar uma cidade, o controller deve retornar essa cidade junto com http status 200")
	void quandoEncontrarCidadeRetornar200() {
		City cityMock = City.builder().build();
		when(cityService.findTemperature(anyString())).thenReturn(Mono.just(cityMock));

		webTestClient.get().uri("/weather/{cityName}", "xpto").exchange()
			.expectStatus().isOk()
			.expectBody(City.class).isEqualTo(cityMock);
	}

	@Test
	@DisplayName("Quando o service não retornar uma cidade, o controller deve retornar a mensagem junto com http status 404")
	void quandoNaoEncontrarCidadeRetornar404() {
		when(cityService.findTemperature(anyString())).thenReturn(Mono.empty());

		webTestClient.get().uri("/weather/{cityName}", "xpto").exchange()
			.expectStatus().isNotFound()
			.expectBody(Map.class).value(response -> assertEquals("Não encontrado", response.get("message")));
	}
}