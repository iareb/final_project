import {Component, EventEmitter, Output} from '@angular/core';
import {UserRegistrationDto} from "../../models/UserRegistrationDto";
import {FormsModule, NgForm} from "@angular/forms";
import {UserService} from "../../services/user/user.service";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  @Output()
  registrationEvent: EventEmitter<string> = new EventEmitter();
  userRegistration: UserRegistrationDto = new UserRegistrationDto();

  constructor(public userService: UserService, public router: Router) {
  }

  onSubmitForm(form: NgForm): void {
    console.log(this.userRegistration);
    if (form.valid) {
      this.userService.userRegistration(this.userRegistration).subscribe(
        res => {
          console.log("result: ", res)
          this.registrationEvent.emit(this.userRegistration.email);
          this.userService.isLogged = true;
          this.userService.userEmail = this.userRegistration.email;
          this.router.navigate(['/']);
        })
    }
  }

}
