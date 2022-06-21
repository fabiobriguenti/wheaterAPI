package com.weather.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseCity {
	
	private String title;
	private String location_type;
	private Integer woeid;
	private String latt_long;
}