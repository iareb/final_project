import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {UserLoginDto} from "../../models/UserLoginDto";
import {NgIf} from "@angular/common";
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  userLogin: UserLoginDto = new UserLoginDto();
  errorMessage: string;
  @Output()
  loginEvent: EventEmitter<string> = new EventEmitter();

  constructor(public userService: UserService, public router: Router) {
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.userService.login(this.userLogin).subscribe({
        next: res => {
          if (res) {
            console.log("Login successful: ", res)
            this.errorMessage = null;
            this.loginEvent.emit(this.userLogin.email);
            this.userService.isLogged = true;
            this.userService.userEmail = this.userLogin.email;
            this.router.navigate(['/']);
          }
        },
        error: err => {
          console.error("Login error: ", err);
          this.errorMessage = "Email o password errata";
        }
      });
    }
  }

}
