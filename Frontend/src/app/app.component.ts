import {Component} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {FooterComponent} from "./footer/footer.component";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {UserService} from "../services/user/user.service";
import {UserDto} from "../models/UserDto";
import {UserLoginDto} from "../models/UserLoginDto";
import {HomeComponent} from "./home/home.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, RegisterComponent, LoginComponent,
    FooterComponent, FormsModule, NgIf, NgForOf, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent { //} implements OnInit{
  title = 'weather-app';

  isLoginPage: boolean = false;
  isRegisterPage: boolean = false;
  userLoggedEmail: string = "Il tuo account";

  constructor(private userService: UserService, private router: Router) {

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = this.router.url === '/login';
        this.isRegisterPage = this.router.url === '/register';
      }
    })
  }
}
