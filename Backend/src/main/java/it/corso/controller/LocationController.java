package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.corso.dto.LocationDto;
import it.corso.service.LocationService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
	@Path("/coordinates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationByCoordinates(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
		
		try {
			LocationDto dto = locationService.getLocationByCoordinates(latitude, longitude);
			return Response.status(Response.Status.OK).entity(dto).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}

}
