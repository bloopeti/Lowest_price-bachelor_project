import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../services/login.service';
import {User} from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', '../app.component.css']
})
export class LoginComponent implements OnInit {

  userToLogin = new User();
  returnedUser = new User();
  isUserLogged: string;
  isUserAdmin: string;
  loggedInUser: string;
  message: string;

  constructor(private router: Router, private loginService: LoginService) {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    this.loggedInUser = localStorage.getItem('loggedInUser');
    this.message = '';
  }

  attemptLogin(): void {
    this.userToLogin.emailAddress = this.userToLogin.emailAddress.trim();
    this.userToLogin.passNoHash = this.userToLogin.passNoHash.trim();
    this.loginService.attemptLogin(this.userToLogin).subscribe(data => {
      this.returnedUser = data;
      if (this.returnedUser.isAdmin === 0) {
        localStorage.setItem('isUserLoggedIn', 'true');
        localStorage.setItem('isUserAdmin', 'false');
        // localStorage.setItem('loggedInUser', JSON.stringify(data));
        localStorage.setItem('loggedInUser', String(this.returnedUser.id));
        this.userToLogin.emailAddress = '';
        this.router.navigate(['home']);
      } else if (this.returnedUser.isAdmin === 1) {
        localStorage.setItem('isUserLoggedIn', 'true');
        localStorage.setItem('isUserAdmin', 'true');
        // localStorage.setItem('loggedInUser', JSON.stringify(data));
        localStorage.setItem('loggedInUser', String(this.returnedUser.id));
        this.userToLogin.emailAddress = '';
        this.router.navigate(['home']);
      } else {
        this.message = 'Wrong email or password!';
      }
      this.isUserLogged = localStorage.getItem('isUserLoggedIn');
      this.isUserAdmin = localStorage.getItem('isUserAdmin');
      this.loggedInUser = localStorage.getItem('loggedInUser');
    });
  }

  logout(): void {
    localStorage.setItem('isUserLoggedIn', 'false');
    localStorage.setItem('isUserAdmin', 'false');
    localStorage.setItem('loggedInUser', '-1');

    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

}
