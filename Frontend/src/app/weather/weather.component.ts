import { Component, OnInit } from '@angular/core';
import {WeatherService} from "../../services/weather/weather.service";
import {NgForOf, NgIf} from "@angular/common";


@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  public latitude: number;
  public longitude: number;
  public weatherData: any;
  public locationData: any;

  constructor(private weatherService: WeatherService) { }

  ngOnInit(): void {
    this.getLocation();
  }

  getLocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.getWeather(this.latitude, this.longitude);
        //this.getLocationInfo(this.latitude, this.longitude);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  getWeather(latitude: number, longitude: number): void {
    this.weatherService.getWeatherByCoordinates(latitude, longitude).subscribe(
      (data) => {
        this.weatherData = data;
      },
      (error) => {
        console.error('Error fetching weather data', error);
      }
    );
  }

}
