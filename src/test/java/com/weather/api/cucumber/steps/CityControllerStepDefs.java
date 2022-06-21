package com.weather.api.cucumber.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.weather.api.controller.CityController;
import com.weather.api.domain.City;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@WebFluxTest(controllers = CityController.class)
public class CityControllerStepDefs {
	
	@Autowired
	private WebTestClient webTestClient;
	
	private ResponseSpec response;
	
	@When("^cliente consulta a cidade (.+)$")
	public void testaGet(String cityName) throws Throwable{
		response = webTestClient.get().uri("/weather/{cityName}", cityName).exchange();
	}

	@Then("^cliente deve receber status (\\d+)$")
	public void clienteRecebeStatus(Integer httpStatus) throws Throwable {
		response
			.expectStatus().isOk()
			.expectBody(City.class);
	}
}