package com.weather.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.weather.api.domain.City;
import com.weather.api.service.CityService;

import reactor.core.publisher.Mono;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping(value="/weather/{cityName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Mono<City> findTemperature (@PathVariable("cityName") String cityName) {
		return cityService.findTemperature(cityName)
			.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado")));
	}
}