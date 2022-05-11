package com.weather.API.domain;

public class Source {

	private String title;
	private String slug;
	private String url;
	private Integer crawl_rate;
	
	public Source() {}

	public Source(String title, String slug, String url, Integer crawl_rate) {
		super();
		this.title = title;
		this.slug = slug;
		this.url = url;
		this.crawl_rate = crawl_rate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCrawl_rate() {
		return crawl_rate;
	}

	public void setCrawl_rate(Integer crawl_rate) {
		this.crawl_rate = crawl_rate;
	}
	
}
