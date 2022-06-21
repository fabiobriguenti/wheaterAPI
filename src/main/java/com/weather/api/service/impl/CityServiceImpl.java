package com.weather.api.service.impl;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.api.domain.City;
import com.weather.api.domain.ConsolidatedWeather;
import com.weather.api.domain.ResponseCity;
import com.weather.api.integration.WeatherIntegration;
import com.weather.api.integration.WoeidIntegration;
import com.weather.api.service.CityService;
import com.weather.api.util.TemperatureConverter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private WoeidIntegration woeidIntegration;
	
	@Autowired
	private WeatherIntegration weatherIntegration;
	
	@Override
	public Mono<City> findTemperature(String cityName) {
		return woeidIntegration.findWoeid(cityName).next()
			.map(ResponseCity::getWoeid)
			.flatMap(weatherIntegration::findFullWeather)
			.flatMap(responseWoeid -> Flux.fromIterable(responseWoeid.getConsolidated_weather())
				.sort(Comparator.comparing(ConsolidatedWeather::getApplicable_date)).next().map(weather -> 
					City.builder()
						.name(responseWoeid.getTitle())
						.woeid(responseWoeid.getWoeid())
						.celsius(weather.getThe_temp())
						.fahrenheit(TemperatureConverter.celsiusToFahrenheit(weather.getThe_temp()))
						.build()));
	}
}