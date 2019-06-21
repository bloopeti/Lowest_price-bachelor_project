import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', '../app.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Zavaczki Peter\'s Bachelor\'s Project';
  isUserLogged: string;
  isUserAdmin: string;
  searchQuery: string;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
  }

  search(query: string): void {
    this.router.navigate(['product/search', query]);
  }

}
