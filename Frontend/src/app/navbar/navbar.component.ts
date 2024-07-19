import {Component, DoCheck, EventEmitter, Input, Output} from '@angular/core';
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgIf,
    RouterLink
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements DoCheck{

  @Input('buyCount')
  buyCount: number;

  @Input('userLoggedEmail')
  email: string;

  @Output()
  event: EventEmitter<string> = new EventEmitter();

  constructor(public userService: UserService) {
  }

  ngDoCheck() {

  }

  /*
    Se viene cliccato il tasto "Login" o il tasto "Registrati" nella navbar,
    viene emesso un evento e inviato al componente padre.
    La stringa 'request' specifica se l'evento è un login o una registrazione.
   */
  request(request: string): void {
    if (request === "login") {
      this.event.emit("login");
    }
    else if (request === "register") {
      this.event.emit("register");
    }
  }

}
