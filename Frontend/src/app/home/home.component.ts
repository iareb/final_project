import {Component, Input} from '@angular/core';
import {WeatherComponent} from "../weather/weather.component";
import {Location} from "../../models/Location";
import {NgForOf} from "@angular/common";
import {WeatherDto} from "../../models/WeatherDto";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    WeatherComponent,
    NgForOf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  @Input('locations')
  locations: Location[];

  @Input('weather')
  weather: WeatherDto;
}
