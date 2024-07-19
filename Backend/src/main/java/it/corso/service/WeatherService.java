package it.corso.service;

import java.util.List;

import it.corso.dto.HourlyTemperatureDto;
import it.corso.dto.HourlyTimeDto;
import it.corso.dto.WeatherDto;
import it.corso.model.Weather;


public interface WeatherService {
	
	Weather getWeatherById(int id);
	
	WeatherDto getWeatherByCoordinates(double latitude, double longitude);
	WeatherDto getWeatherByLocation(int locationId);
	
	List<HourlyTimeDto> getWeatherHours(int weatherId);
	List<HourlyTemperatureDto> getWeatherTemperatues(int weatherId);
	

}
