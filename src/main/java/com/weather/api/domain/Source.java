package com.weather.api.domain;

import lombok.Data;

@Data
public class Source {

	private String title;
	private String slug;
	private String url;
	private Integer crawl_rate;
}