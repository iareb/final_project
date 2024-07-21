package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Location;
import it.corso.model.Weather;

public interface WeatherDao extends CrudRepository<Weather, Integer> {
	
	Optional<Weather> findByLocation(Location location);
	
}
