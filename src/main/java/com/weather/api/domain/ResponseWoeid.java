package com.weather.api.domain;

import java.util.ArrayList;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseWoeid extends ResponseCity {
	
	private ArrayList<ConsolidatedWeather> consolidated_weather;
	private Calendar time;
	private Calendar sun_rise;
	private Calendar sun_set;
	private String timezone_name;
	private ResponseCity parent;
	private ArrayList<Source> sources;
	private String timezone;
}