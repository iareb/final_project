package it.corso.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.LocationDao;
import it.corso.dto.LocationDto;
import it.corso.model.Location;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LocationDto getLocationById(int id) {
		Optional<Location> optional = locationDao.findById(id);
		if (!optional.isPresent()) {
			return new LocationDto();
		}
		Location location = optional.get();
		LocationDto locationDto = modelMapper.map(location, LocationDto.class);
		return locationDto;
	}
	
	@Override
	public LocationDto getLocationByName(String name) {
		Optional<Location> optional = locationDao.findByName(name);
		if (!optional.isPresent()) {
			return new LocationDto();
		}
		Location location = optional.get();
		LocationDto locationDto = modelMapper.map(location, LocationDto.class);
		return locationDto;
	}


	@Override
	public List<LocationDto> getAllLocations() {
		List<Location> locations = (List<Location>) locationDao.findAll();
		List<LocationDto> locationsDto = modelMapper.map(locations, new TypeToken<List<LocationDto>>() {}.getType());
		return locationsDto;
	}
}
