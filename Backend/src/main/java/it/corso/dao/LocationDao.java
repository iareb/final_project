package it.corso.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Location;

public interface LocationDao extends CrudRepository<Location, Integer> {
	
	Optional<Location> findByName(String name);
	
}
