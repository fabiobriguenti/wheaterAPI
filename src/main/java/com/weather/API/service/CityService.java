package com.weather.API.service;

import com.weather.API.domain.City;

public interface CityService {

	public City findTemperature(String cityName);
}
