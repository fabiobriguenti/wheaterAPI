package com.weather.API.domain;

public class City {

	private String name;
	private Integer woeid;
	private Double temp_celsius;
	private Double temp_farenheit;
	
	public City() {}

	public City(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWoeid() {
		return woeid;
	}

	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	public Double getTemp_celsius() {
		return temp_celsius;
	}

	public void setTemp_celsius(Double temp_celsius) {
		this.temp_celsius = temp_celsius;
	}

	public Double getTemp_farenheit() {
		return temp_farenheit;
	}

	public void setTemp_farenheit(Double temp_farenheit) {
		this.temp_farenheit = temp_farenheit;
	}
	
	
}
