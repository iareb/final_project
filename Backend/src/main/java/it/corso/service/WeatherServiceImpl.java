package it.corso.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.corso.dao.LocationDao;
import it.corso.dao.WeatherDao;
import it.corso.dto.WeatherDto;
import it.corso.model.Location;
import it.corso.model.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private WebClient.Builder webClientBuilder;
    	
	@Override
	public WeatherDto getWeatherById(int id) {
		Optional<Weather> optional = weatherDao.findById(id);
		if (!optional.isPresent()) {
			return new WeatherDto();
		}
		Weather weather = optional.get();
		return modelMapper.map(weather, WeatherDto.class);
	}
	
	@Override
	public WeatherDto fetchWeatherData(int locationId) {
		Optional<Location> optional = locationDao.findById(locationId);
	    Weather weather = new Weather();
		if (optional.isPresent()) {
			
		   Location location = optional.get();	    
		   WebClient webClient = webClientBuilder.build();
	       try {
	    	   
		    	Map<String, Object> responseBody = webClient.get()
		    			.uri(API_URL, location.getLatitude(), location.getLongitude())
		    			.retrieve()
		    			.bodyToMono(Map.class)
		    			.block();
			   
		      if (responseBody != null) {
		        
			       Map<String, Object> currentWeather = (Map<String, Object>) responseBody.get("current");
			       Map<String, Object> hourlyData = (Map<String, Object>) responseBody.get("hourly");
			       
			       weather.setLocation(location);
			       weather.setTimestamp(LocalDateTime.now());
			       weather.setCurrentTemperature((Double) currentWeather.get("temperature_2m"));
			       weather.setCurrentWindSpeed((Double) currentWeather.get("wind_speed_10m"));
	
		       	   weather.setHourlyTime(new ObjectMapper().writeValueAsString(hourlyData.get("time")));
		    	   weather.setHourlyTemperature(new ObjectMapper().writeValueAsString(hourlyData.get("temperature_2m")));
		    	   weather.setHourlyHumidity(new ObjectMapper().writeValueAsString(hourlyData.get("relative_humidity_2m")));
		           weather.setHourlyWindSpeed(new ObjectMapper().writeValueAsString(hourlyData.get("wind_speed_10m")));
	      	}
		    else {
              throw new RuntimeException("Empty response from Open-Meteo");
          	 }
	       }
	       catch (JsonProcessingException e) {
			e.printStackTrace();
	       }
		}
		
		WeatherDto dto = modelMapper.map(weather, WeatherDto.class);
		return dto;
	}

	@Override
	public WeatherDto fetchAndSaveWeatherData(int locationId) {
		
		Optional<Location> optional = locationDao.findById(locationId);
	    Weather weather = new Weather();
		if (optional.isPresent()) {
			
		   Location location = optional.get();	    
		   WebClient webClient = webClientBuilder.build();
	       try {
	    	   
		    	Map<String, Object> responseBody = webClient.get()
		    			.uri(API_URL, location.getLatitude(), location.getLongitude())
		    			.retrieve()
		    			.bodyToMono(Map.class)
		    			.block();
			   
		      if (responseBody != null) {
		        
			       Map<String, Object> currentWeather = (Map<String, Object>) responseBody.get("current");
			       Map<String, Object> hourlyData = (Map<String, Object>) responseBody.get("hourly");
			       
			       weather.setLocation(location);
			       weather.setTimestamp(LocalDateTime.now());
			       weather.setCurrentTemperature((Double) currentWeather.get("temperature_2m"));
			       weather.setCurrentWindSpeed((Double) currentWeather.get("wind_speed_10m"));
	
		       	   weather.setHourlyTime(new ObjectMapper().writeValueAsString(hourlyData.get("time")));
		    	   weather.setHourlyTemperature(new ObjectMapper().writeValueAsString(hourlyData.get("temperature_2m")));
		    	   weather.setHourlyHumidity(new ObjectMapper().writeValueAsString(hourlyData.get("relative_humidity_2m")));
		           weather.setHourlyWindSpeed(new ObjectMapper().writeValueAsString(hourlyData.get("wind_speed_10m")));
		           weatherDao.save(weather);
	      	}
		    else {
              throw new RuntimeException("Empty response from Open-Meteo");
          	 }
	       }
	       catch (JsonProcessingException e) {
			e.printStackTrace();
	       }
		}
		
		WeatherDto dto = modelMapper.map(weather, WeatherDto.class);
		return dto;
	}

	@Override
	public void deleteWeatherById(int id) {
		Optional<Weather> optional = weatherDao.findById(id);
		if (optional.isPresent()) {
			weatherDao.delete(optional.get());
		}
	}

	@Override
	public WeatherDto getWeatherByLocationName(String name) {
		Optional<Location> optional = locationDao.findByName(name);
		if (optional.isPresent()) {
			Location location = optional.get();
			WeatherDto dto = fetchWeatherData(location.getId());
			return dto;
		}
		return null;
	}

}
