package it.corso.service;

import it.corso.dto.WeatherDto;

public interface WeatherService {
	
	// URL per le richieste all'API di Open-Meteo
	String API_URL = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";
	String API_URL_CURRENT_WEATHER = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily&current=temperature_2m,wind_speed_10m";
	String API_URL_HOURLY_WEATHER = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";	
	
	WeatherDto getWeatherById(int id);
	void deleteWeatherById(int id);
	WeatherDto getWeatherByLocationName(String name);
	
	WeatherDto fetchWeatherData(int locationId);
	WeatherDto fetchCurrentWeather(int locationId);
	WeatherDto fetchHourlyWeather(int locationId);
	
}
