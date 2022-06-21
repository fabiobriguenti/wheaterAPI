package com.weather.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemperatureConverter {
	private static final BigDecimal FAHRENHEIT_MULTIPLIER = new BigDecimal("1.8");
	private static final BigDecimal FAHRENHEIT_AUGEND = new BigDecimal("32");
	
	public static Number celsiusToFahrenheit(Number celsius) {
		return FAHRENHEIT_AUGEND.add(FAHRENHEIT_MULTIPLIER.multiply(new BigDecimal(celsius.toString()))).setScale(3, RoundingMode.HALF_UP);
	}
	
//	@SuppressWarnings("unchecked")
//	public static <T extends Number> T celsiusToFahrenheit(T celsius) {
//		return (T) NumberUtils.convertNumberToTargetClass(FAHRENHEIT_AUGEND.add(FAHRENHEIT_MULTIPLIER.multiply(new BigDecimal(celsius.toString()))), celsius.getClass());
//	}
}