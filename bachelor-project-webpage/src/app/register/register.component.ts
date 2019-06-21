import {Component, OnInit} from '@angular/core';
import {RegisterService} from '../services/register.service';
import {User} from '../model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css', '../app.component.css']
})
export class RegisterComponent implements OnInit {

  userToRegister = new User();
  isUserLogged: string;
  message: string;

  constructor(private registerService: RegisterService) {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
  }

  attemptRegister(): void {
    this.userToRegister.emailAddress = this.userToRegister.emailAddress.trim();
    this.userToRegister.passNoHash = this.userToRegister.passNoHash.trim();
    this.userToRegister.passNoHashRepeat = this.userToRegister.passNoHashRepeat.trim();
    this.registerService.attemptRegister(this.userToRegister).subscribe(data => {
      let response: string;
      response = data;
      if (response.includes('USER REGISTER SUCCESSFUL')) {
        this.message = 'Successfully registered!\nUse your new account to log in';
        this.userToRegister.emailAddress = '';
      } else if (response.includes('USER REGISTER FAILED: Passwords don\'t match')) {
        this.message = 'Registration failed!\nThe inputted passwords did not match!';
      } else {
        this.message = 'An error happened!\nRegistration unsuccessful';
      }
    });
  }

}
