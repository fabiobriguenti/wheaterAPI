package com.weather.api.service;

import com.weather.api.domain.City;

import reactor.core.publisher.Mono;

public interface CityService {
	public Mono<City> findTemperature(String cityName);
}