package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hourly_temperature")
public class HourlyTemperature {

	@Id
	@Column(name = "hourly_temperature_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "temperature_2m")
	private double temperature;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "weather_id", referencedColumnName = "weather_id")
	private Weather weather;

	public HourlyTemperature(Weather weather, Double temp) {
		this.weather = weather;
		this.temperature = temp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
}
