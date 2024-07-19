package it.corso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import it.corso.dao.LocationDao;
import it.corso.dao.WeatherDao;
import it.corso.dto.HourlyTemperatureDto;
import it.corso.dto.HourlyTimeDto;
import it.corso.dto.WeatherDto;
import it.corso.model.HourlyTemperature;
import it.corso.model.HourlyTime;
import it.corso.model.Location;
import it.corso.model.Weather;
import reactor.core.publisher.Mono;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Autowired
	private LocationDao locationDao;

	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public Weather getWeatherById(int id) {
		Optional<Weather> optional = weatherDao.findById(id);
		if (!optional.isPresent()) {
			return new Weather();
		}
		return optional.get();
	}
	

	public WeatherDto getWeatherByCoordinates(double latitude, double longitude) {
	        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature_2m", latitude, longitude);

	        // Effettua una chiamata HTTP e ritorna un Mono che emette Weather
	        Mono<Weather> responseMono = webClient.get()
	                .uri(url)
	                .retrieve()
	                .bodyToMono(Weather.class);

	        // Blocca l'esecuzione fino a quando il Mono non emette un valore o un errore
	        Weather response = responseMono.block();
	        

	        if (response != null) {
	        	
	            response.setLatitude(latitude);
	            response.setLongitude(longitude);
	        	
	            // Mappa i dati di 'hourly_time' e 'hourly_temperature'
	            List<HourlyTime> hourlyTimes = response.getHourly().getTime().stream()
	                .map(time -> new HourlyTime(response, time))
	                .collect(Collectors.toList());
	            response.setHourlyTimes(hourlyTimes);

	            List<HourlyTemperature> hourlyTemperatures = response.getHourly().getTemperature_2m().stream()
	                .map(temp -> new HourlyTemperature(response, temp))
	                .collect(Collectors.toList());
	            response.setHourlyTemperatures(hourlyTemperatures);

	            // Salva la risposta nel database
	            weatherDao.save(response);
	    		//return modelMapper.map(response, WeatherDto.class);
	        }

	        return null;
	    }


	@Override
	public WeatherDto getWeatherByLocation(int locationId) {

       Optional<Location> optional = locationDao.findById(locationId);
       Location location = null;
       if (optional.isPresent()) {
    	   location = optional.get();
       }
       Weather weather = null;
       Optional<Weather> optionalWeather = weatherDao.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());
       if (optionalWeather.isPresent()) {
    	   weather = optionalWeather.get();
       }

       return modelMapper.map(weather, WeatherDto.class);
	}
	
	


	@Override
	public List<HourlyTimeDto> getWeatherHours(int weatherId) {
		Optional<Weather> optional = weatherDao.findById(weatherId);
		Weather weatherDb = null;
		if (optional.isPresent()) {
			weatherDb = optional.get();
		}
		List<HourlyTime> times = weatherDb.getHourlyTimes();
		return modelMapper.map(times, new TypeToken<List<HourlyTimeDto>>() {}.getType());
 	}


	@Override
	public List<HourlyTemperatureDto> getWeatherTemperatues(int weatherId) {
		Optional<Weather> optional = weatherDao.findById(weatherId);
		Weather weatherDb = null;
		if (optional.isPresent()) {
			weatherDb = optional.get();
		}
		List<HourlyTemperature> temperatures = weatherDb.getHourlyTemperatures();
		return modelMapper.map(temperatures, new TypeToken<List<HourlyTemperatureDto>>() {}.getType());
	}
	
}
