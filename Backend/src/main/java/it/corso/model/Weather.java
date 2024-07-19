package it.corso.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "weather")
public class Weather {
	
	@Id
	@Column(name = "weather_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "latitude", nullable = false)
	private double latitude;
	
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "latitude", referencedColumnName = "latitude", insertable = false, updatable = false),
        @JoinColumn(name = "longitude", referencedColumnName = "longitude", insertable = false, updatable = false)
    })
    private Location location;
	
	@OneToMany(mappedBy = "weather", cascade = CascadeType.ALL)
	private List<HourlyTime> hourlyTimes;
	
	@OneToMany(mappedBy = "weather", cascade = CascadeType.ALL)
	private List<HourlyTemperature> hourlyTemperatures;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public List<HourlyTime> getHourlyTimes() {
		return hourlyTimes;
	}

	public void setHourlyTimes(List<HourlyTime> hourlyTimes) {
		this.hourlyTimes = hourlyTimes;
	}

	public List<HourlyTemperature> getHourlyTemperatures() {
		return hourlyTemperatures;
	}

	public void setHourlyTemperatures(List<HourlyTemperature> hourlyTemperatures) {
		this.hourlyTemperatures = hourlyTemperatures;
	}	
	
	
	// Inner Hourly class to match JSON structure
    public static class Hourly {
        private List<String> time;
        private List<Double> temperature_2m;

        // Getters and setters
        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }

        public void setTemperature_2m(List<Double> temperature_2m) {
            this.temperature_2m = temperature_2m;
        }
    }

    @Transient
    private Hourly hourly;

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }
}
