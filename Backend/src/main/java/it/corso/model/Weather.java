package it.corso.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weather_id")
	private int id;
	
	@Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "current_temperature")
    private double currentTemperature;

    @Column(name = "current_wind_speed")
    private double currentWindSpeed;

    @Column(name = "hourly_time")
    private String hourlyTime; // JSON data as a String

    @Column(name = "hourly_temperature")
    private String hourlyTemperature; // JSON data as a String

    @Column(name = "hourly_humidity")
    private String hourlyHumidity; // JSON data as a String

    @Column(name = "hourly_wind_speed")
    private String hourlyWindSpeed; 
    
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public double getCurrentTemperature() {
		return currentTemperature;
	}

	public void setCurrentTemperature(double currentTemperature) {
		this.currentTemperature = currentTemperature;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
