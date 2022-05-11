package com.weather.API.domain;

import java.util.ArrayList;
import java.util.Calendar;

public class ResponseWoeid extends ResponseCity{
	
	private ArrayList<ConsolidatedWeather> consolidated_weather;
	private Calendar time;
	private Calendar sun_rise;
	private Calendar sun_set;
	private String timezone_name;
	private ResponseCity parent;
	private ArrayList<Source> sources;
	private String timezone;
	
	public ResponseWoeid() {}

	public ResponseWoeid(ArrayList<ConsolidatedWeather> consolidated_weather, Calendar time, Calendar sun_rise,
			Calendar sun_set, String timezone_name, ResponseCity parent, ArrayList<Source> sources, String timezone) {
		super();
		this.consolidated_weather = consolidated_weather;
		this.time = time;
		this.sun_rise = sun_rise;
		this.sun_set = sun_set;
		this.timezone_name = timezone_name;
		this.parent = parent;
		this.sources = sources;
		this.timezone = timezone;
	}

	public ArrayList<ConsolidatedWeather> getConsolidated_weather() {
		return consolidated_weather;
	}

	public void setConsolidated_weather(ArrayList<ConsolidatedWeather> consolidated_weather) {
		this.consolidated_weather = consolidated_weather;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public Calendar getSun_rise() {
		return sun_rise;
	}

	public void setSun_rise(Calendar sun_rise) {
		this.sun_rise = sun_rise;
	}

	public Calendar getSun_set() {
		return sun_set;
	}

	public void setSun_set(Calendar sun_set) {
		this.sun_set = sun_set;
	}

	public String getTimezone_name() {
		return timezone_name;
	}

	public void setTimezone_name(String timezone_name) {
		this.timezone_name = timezone_name;
	}

	public ResponseCity getParent() {
		return parent;
	}

	public void setParent(ResponseCity parent) {
		this.parent = parent;
	}

	public ArrayList<Source> getSources() {
		return sources;
	}

	public void setSources(ArrayList<Source> sources) {
		this.sources = sources;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}
