import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Zavaczki Peter\'s Bachelor\' Project';
  isUserLogged: string;
  isUserAdmin: string;

  constructor() {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

}
