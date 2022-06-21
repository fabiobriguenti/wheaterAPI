package com.weather.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@EqualsAndHashCode
public class City {

	private String name;
	
	private Integer woeid;
	
	@JsonProperty("temp_celsius")
	private Number celsius;
	
	@JsonProperty("temp_fahrenheit")
	private Number fahrenheit;
}