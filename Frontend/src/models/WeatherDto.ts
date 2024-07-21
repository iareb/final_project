export class WeatherDto {

  currentTemperature: number;
  currentWindSpeed: number;
  timestamp: string;
  hourlyTime: string;
  hourlyTemperature: string;
  hourlyHumidity: string;
  hourlyWindSpeed: string;
  hourlyWeather: {
    time: Date;
    temperature: number;
    humidity: number;
    windSpeed: number;
  }[];

  constructor(currentTemperature: number, currentWindSpeed: number, timestamp: string, hourlyTime: string,
              hourlyTemperature: string, hourlyHumidity: string, hourlyWindSpeed: string) {

    this.currentTemperature = currentTemperature;
    this.currentWindSpeed = currentWindSpeed;
    this.timestamp = timestamp;
    this.hourlyTime = hourlyTime;
    this.hourlyTemperature = hourlyTemperature;
    this.hourlyHumidity = hourlyHumidity;
    this.hourlyWindSpeed = hourlyWindSpeed;
  }

}
