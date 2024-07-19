package it.corso.service;

import it.corso.dto.LocationDto;

public interface LocationService {
	
	LocationDto getLocationById(int id);
	LocationDto getLocationByName(String name);
	LocationDto getLocationByCoordinates(double latitude, double longitude);
}
