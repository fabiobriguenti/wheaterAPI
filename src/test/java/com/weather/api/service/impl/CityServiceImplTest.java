package com.weather.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weather.api.domain.ResponseCity;
import com.weather.api.domain.ResponseWoeid;
import com.weather.api.integration.WeatherIntegration;
import com.weather.api.integration.WoeidIntegration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class CityServiceImplTest {
	
	@Mock
	private WoeidIntegration woeidIntegration;
	
	@Mock
	private WeatherIntegration weatherIntegration;
	
	@InjectMocks
	private CityServiceImpl cityServiceImpl;
	
	@Value("classpath:/ResponseCityMock.json")
	private Resource responseCityResource;
	
	@Value("classpath:/ResponseWoeidMock.json")
	private Resource responseWoeidResource;
	
	static private ObjectMapper mapper;
	
	@BeforeAll
	static void setup() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}

	@Test
	void test() throws IOException {
		List<ResponseCity> responseCityMock = mapper.readValue(responseCityResource.getFile(), mapper.getTypeFactory().constructCollectionType(List.class, ResponseCity.class));
		ResponseWoeid responseWoeidMock = mapper.readValue(responseWoeidResource.getFile(), ResponseWoeid.class);
		
		when(woeidIntegration.findWoeid(anyString())).thenReturn(Flux.fromIterable(responseCityMock));
		when(weatherIntegration.findFullWeather(anyInt())).thenReturn(Mono.just(responseWoeidMock));
		
		StepVerifier.create(cityServiceImpl.findTemperature("xpto"))
			.consumeNextWith(result -> {
				assertEquals("Toronto", result.getName());
				assertEquals("16.436", result.getCelsius().toString());
				assertEquals("61.585", result.getFahrenheit().toString());
			})
			.verifyComplete();
		
		verify(woeidIntegration, times(1)).findWoeid(anyString());
		verify(weatherIntegration, times(1)).findFullWeather(anyInt());
	}
	
	@Test
	void testeChamadinhas() throws IOException {
		List<ResponseCity> responseCityMock = mapper.readValue(responseCityResource.getFile(), mapper.getTypeFactory().constructCollectionType(List.class, ResponseCity.class));
		ResponseWoeid responseWoeidMock = mapper.readValue(responseWoeidResource.getFile(), ResponseWoeid.class);
		
		when(woeidIntegration.findWoeid(anyString())).thenReturn(Flux.empty());
		when(weatherIntegration.findFullWeather(anyInt())).thenReturn(Mono.just(responseWoeidMock));
		
		StepVerifier.create(cityServiceImpl.findTemperature("xpto")).verifyComplete();
		
		verify(woeidIntegration, times(1)).findWoeid(anyString());
		verify(weatherIntegration, times(1)).findFullWeather(anyInt());
	}
}