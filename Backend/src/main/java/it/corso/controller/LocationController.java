package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.corso.dto.LocationDto;
import it.corso.model.Location;
import it.corso.service.LocationService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RestController
@Path("/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationById(@PathParam("id") int id) {
		
		try {	
			LocationDto locationDto = locationService.getLocationById(id);
			return Response.status(Response.Status.OK).entity(locationDto).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationByName(@QueryParam("name") String name) {
		try {
			LocationDto locationDto = locationService.getLocationByName(name);
			return Response.status(Response.Status.OK).entity(locationDto).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLocations() {
		
		try {
			List<LocationDto> locationList = locationService.getAllLocations();
			return Response.status(Response.Status.OK).entity(locationList).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
}
