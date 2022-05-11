package com.weather.API.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.weather.API.domain.City;
import com.weather.API.service.CityService;

@RestController
public class CityController {

	@Inject
	private CityService cityService;
	
	@GetMapping(value="/search/{cityName}/", produces="application/json")
	public ResponseEntity<City> findTemperature (@PathVariable("cityName") String cityName){
		return new ResponseEntity<>(cityService.findTemperature(cityName), HttpStatus.OK);
	}
}
