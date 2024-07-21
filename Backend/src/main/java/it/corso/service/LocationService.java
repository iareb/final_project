package it.corso.service;

import java.util.List;

import it.corso.dto.LocationDto;

public interface LocationService {
	
	LocationDto getLocationById(int id);
	LocationDto getLocationByName(String name);
	List<LocationDto> getAllLocations();
	
}
