import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css', '../app.component.css']
})
export class NavbarComponent implements OnInit {

  isUserLogged: string;
  isUserAdmin: string;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');

    this.router.events.subscribe(event => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isUserLogged = localStorage.getItem('isUserLoggedIn');
        this.isUserAdmin = localStorage.getItem('isUserAdmin');
      }
    });
  }

  logout(): void {
    localStorage.setItem('isUserLoggedIn', 'false');
    localStorage.setItem('isUserAdmin', 'false');
    localStorage.setItem('loggedInUser', '-1');

    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    // window.location.reload();
    this.router.navigate(['home']);
  }

}
