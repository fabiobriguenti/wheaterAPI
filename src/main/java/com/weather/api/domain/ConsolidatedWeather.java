package com.weather.api.domain;

import java.time.LocalDate;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConsolidatedWeather {

	private Long id;
	private String weather_state_name;
	private String weather_state_abbr;
	private String wind_direction_compass;
	private Calendar created;
	private LocalDate applicable_date;
	private Double min_temp;
	private Double max_temp;
	private Double the_temp;
	private Double wind_speed;
	private Double wind_direction;
	private Double air_pressure;
	private Integer humidity;
	private Double visibility;
	private Integer predictability;
}