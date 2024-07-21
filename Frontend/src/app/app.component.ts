import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {FooterComponent} from "./footer/footer.component";
import {FormsModule, NgForm} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {UserService} from "../services/user/user.service";
import {UserDto} from "../models/UserDto";
import {UserLoginDto} from "../models/UserLoginDto";
import {HomeComponent} from "./home/home.component";
import {Location} from "../models/Location";
import {LocationService} from "../services/location/location.service";
import {WeatherDto} from "../models/WeatherDto";
import {WeatherService} from "../services/weather/weather.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, RegisterComponent, LoginComponent,
    FooterComponent, FormsModule, NgIf, NgForOf, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'gestionale_corsi';

  isLoginPage: boolean = false;
  isRegisterPage: boolean = false;

  locations: Location[];
  weather: WeatherDto;

  userLoggedEmail: string = "Il tuo account";

  constructor(private userService: UserService, private locationService: LocationService,
              private weatherService: WeatherService, private router: Router) {

    this.locations = this.sendLocations();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = this.router.url === '/login';
        this.isRegisterPage = this.router.url === '/register';

      }
    })
  }

  ngOnInit() {
    this.weatherService.getWeatherData(1).subscribe(
      data => {
        this.weather = data;
      });
  }

  sendLocations(): Location[] {
    this.locationService.getAllLocations().subscribe(
      result => {
        this.locations = result as Location[]
      });
    return this.locations;
  }


}
