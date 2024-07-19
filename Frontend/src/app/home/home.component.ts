import { Component } from '@angular/core';
import {WeatherComponent} from "../weather/weather.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    WeatherComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
