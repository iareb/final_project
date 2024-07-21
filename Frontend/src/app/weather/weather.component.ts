import {Component, Input} from '@angular/core';
import {Location} from "../../models/Location";
import {WeatherDto} from "../../models/WeatherDto";
import {DatePipe, NgClass, NgForOf} from "@angular/common";

@Component({
  selector: 'app-weather',
  standalone: true,
  imports: [
    NgClass,
    NgForOf,
    DatePipe
  ],
  templateUrl: './weather.component.html',
  styleUrl: './weather.component.css'
})
export class WeatherComponent {

  @Input('location')
  location: Location;

  @Input('weather')
  weather: WeatherDto;

}
