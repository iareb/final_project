package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.corso.dto.HourlyTemperatureDto;
import it.corso.dto.HourlyTimeDto;
import it.corso.dto.WeatherDetailsDto;
import it.corso.dto.WeatherDto;
import it.corso.model.Weather;
import it.corso.service.WeatherService;
import jakarta.ws.rs.Consumes;
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
			Weather weather = weatherService.getWeatherById(id);
			return Response.status(Response.Status.OK).entity(weather).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherByLatitudeAndLongitude(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
		try {
			WeatherDto weather = weatherService.getWeatherByCoordinates(latitude, longitude);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
	}
	
	@GET
	@Path("/location/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherByLocation(@PathParam("id") int locationId) {
		try {
			WeatherDto weather = weatherService.getWeatherByLocation(locationId);
			return Response.status(Response.Status.OK).entity(weather).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}	
	}
	
	@GET
	@Path("/{id}/times")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherHours(@PathParam("id") int id) {
		try {
			List<HourlyTimeDto> times = weatherService.getWeatherHours(id);
			return Response.status(Response.Status.OK).entity(times).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/{id}/temperatures")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWeatherTemperatures(@PathParam("id") int id) {
		try {
			List<HourlyTemperatureDto> temperatures = weatherService.getWeatherTemperatues(id);
			return Response.status(Response.Status.OK).entity(temperatures).build();
		} 
		catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
