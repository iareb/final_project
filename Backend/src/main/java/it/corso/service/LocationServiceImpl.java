package it.corso.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.LocationDao;
import it.corso.dto.LocationDto;
import it.corso.model.Location;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public LocationDto getLocationById(int id) {
		Optional<Location> optional = locationDao.findById(id);
		if (!optional.isPresent()) {
			return new LocationDto();
		}
		
		Location locationDb = optional.get();
		LocationDto locationDto = modelMapper.map(locationDb, LocationDto.class);
		return locationDto;
	}

	@Override
	public LocationDto getLocationByName(String name) {
		Optional<Location> optional = locationDao.findByName(name);
		if (!optional.isPresent()) {
			return new LocationDto();
		}
		
		Location locationDb = optional.get();
		LocationDto locationDto = modelMapper.map(locationDb, LocationDto.class);
		return locationDto;
	}

	@Override
	public LocationDto getLocationByCoordinates(double latitude, double longitude) {
		Optional<Location> optional = locationDao.findByLatitudeAndLongitude(latitude, longitude);
		if (!optional.isPresent()) {
			return new LocationDto();
		}
		Location locationDb = optional.get();
		LocationDto locationDto = modelMapper.map(locationDb, LocationDto.class);
		return locationDto;
	}
	
}
