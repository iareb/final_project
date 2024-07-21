package it.corso.service;

import it.corso.dto.WeatherDto;

public interface WeatherService {
	
	// URL per le richieste all'API di Open-Meteo
	String API_URL = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&daily"
			+ "&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";
	
	/* Questa stringa contiene modifiche carine da aggiungere: verifica se è giorno notte, probabilità di pioggia ecc.
	String API_URL = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&current=temperature_2m,relative_humidity_2m,is_day,precipitation,wind_speed_10m"
			+ "&hourly=temperature_2m,precipitation_probability,rain,weather_code,is_day";
	*/
	
	WeatherDto getWeatherById(int id);
	void deleteWeatherById(int id);
	WeatherDto getWeatherByLocationName(String name);
	
	WeatherDto fetchWeatherData(int locationId);
	WeatherDto fetchAndSaveWeatherData(int locationId);
	
}
