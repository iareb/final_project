package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.corso.dto.WeatherDto;
import it.corso.service.WeatherService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RestController
@Path("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherById(@PathParam("id") int id) {
		try {
			WeatherDto weather = weatherService.getWeatherById(id);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteWeatherById(@PathParam("id") int id) {
		try {
			weatherService.deleteWeatherById(id);
			return Response.status(Response.Status.OK).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/fetch/{locationId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchWeatherData(@PathParam("locationId") int locationId) {
		try {
			WeatherDto weather = weatherService.fetchWeatherData(locationId);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@GET
	@Path("/fetch/{locationId}/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fetchAndSaveWeatherData(@PathParam("locationId") int locationId) {
		try {
			WeatherDto weather = weatherService.fetchAndSaveWeatherData(locationId);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	
	@GET
	@Path("/location")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherByLocationName(@QueryParam("name") String locationName) {
		try {
			WeatherDto weather = weatherService.getWeatherByLocationName(locationName);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
