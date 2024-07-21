package it.corso.dto;

import java.time.LocalDateTime;

public class WeatherDto {
	
	private LocalDateTime timestamp;
	private double currentTemperature;
    private double currentWindSpeed;
    private String hourlyTime;
    private String hourlyTemperature; 
    private String hourlyHumidity; 
    private String hourlyWindSpeed;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public double getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(double current_temperature) {
		this.currentTemperature = current_temperature;
	}
	public double getCurrentWindSpeed() {
		return currentWindSpeed;
	}
	public void setCurrentWindSpeed(double currentWindSpeed) {
		this.currentWindSpeed = currentWindSpeed;
	}
	public String getHourlyTime() {
		return hourlyTime;
	}
	public void setHourlyTime(String hourlyTime) {
		this.hourlyTime = hourlyTime;
	}
	public String getHourlyTemperature() {
		return hourlyTemperature;
	}
	public void setHourlyTemperature(String hourlyTemperature) {
		this.hourlyTemperature = hourlyTemperature;
	}
	public String getHourlyHumidity() {
		return hourlyHumidity;
	}
	public void setHourlyHumidity(String hourlyHumidity) {
		this.hourlyHumidity = hourlyHumidity;
	}
	public String getHourlyWindSpeed() {
		return hourlyWindSpeed;
	}
	public void setHourlyWindSpeed(String hourlyWindSpeed) {
		this.hourlyWindSpeed = hourlyWindSpeed;
	} 
    
    

}
