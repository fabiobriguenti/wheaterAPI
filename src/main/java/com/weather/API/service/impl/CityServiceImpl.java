package com.weather.API.service.impl;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Named;

import com.weather.API.domain.City;
import com.weather.API.integration.FullWeatherIntegration;
import com.weather.API.integration.WoeidIntegration;
import com.weather.API.service.CityService;
import com.weather.API.util.ConversorUtil;

@Named
public class CityServiceImpl implements CityService{

	@Inject
	private WoeidIntegration woeidIntegration;
	
	@Inject
	private FullWeatherIntegration fullWeatherIntegration;
	
	@Override
	public City findTemperature(String cityName) {
		return woeidIntegration.findWoeid(cityName).flatMap(response -> {
			return fullWeatherIntegration.findFullWeather(response.get(0).getWoeid()).map(responseWoeid -> {
				 return responseWoeid.getConsolidated_weather().stream()
						 .filter(consolidatedWeather -> consolidatedWeather.getApplicable_date().equals(LocalDate.now()))
						 .findFirst().map(obj -> {
							 City city = new City();
							 city.setName(cityName);
							 city.setWoeid(responseWoeid.getWoeid());
							 city.setTemp_celsius(obj.getThe_temp());
							 city.setTemp_farenheit(ConversorUtil.celsiusToFarenheit(obj.getThe_temp()));
							 return city;
						 });
			});	
		}).block().get();
	}

}
