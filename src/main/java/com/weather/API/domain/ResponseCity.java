package com.weather.API.domain;

public class ResponseCity {
	
	private String title;
	private String location_type;
	private Integer woeid;
	private String latt_long;
	
	public ResponseCity() {}
	
	public ResponseCity(String title, String location_type, Integer woeid, String latt_long) {
		super();
		this.title = title;
		this.location_type = location_type;
		this.woeid = woeid;
		this.latt_long = latt_long;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public Integer getWoeid() {
		return woeid;
	}

	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}

	public String getLatt_long() {
		return latt_long;
	}

	public void setLatt_long(String latt_long) {
		this.latt_long = latt_long;
	}
	
	
}
