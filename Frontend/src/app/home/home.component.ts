import {Component, Input, OnInit} from '@angular/core';
import {WeatherComponent} from "../weather/weather.component";
import {Location} from "../../models/Location";
import {NgForOf} from "@angular/common";
import {WeatherDto} from "../../models/WeatherDto";
import {FormsModule} from "@angular/forms";
import {WeatherService} from "../../services/weather/weather.service";
import {LocationService} from "../../services/location/location.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    WeatherComponent,
    NgForOf,
    FormsModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  location: Location;

  weather: WeatherDto;

  cityName = '';

  constructor(private weatherService: WeatherService, private locationService: LocationService) {
  }

  // Inizialmente, il componente mostra le informazioni meteo di Messina.
  ngOnInit() {
    this.fetchWeatherByLocationName('Messina');
  }

  /*
    È possibile cercare informazioni di un'altra città.
    In questo momento, nel database esiste solo un'altra località, Palermo.
   */
  fetchWeatherByLocationName(cityName: string): void {

    this.locationService.getLocationByName(cityName).subscribe(
      data => {
        this.location = data;
      }
    )
    this.weatherService.fetchWeatherDataByLocationName(cityName).subscribe(
      data => {
        this.weather = data;
      }, error => {
        console.log("Error fetching data, ", error);
      });
  }

  searchCity(cityName: string): void {
    this.fetchWeatherByLocationName(cityName);
  }
}
