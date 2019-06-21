import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';


const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class FavouriteProductService {

  userUrl = 'http://localhost:9906/app/user';

  constructor(private http: HttpClient) {
  }

  // the userDto parameter should only contain the ID of the user
  // and one single element in the list of the favourite products,
  // containing the id of the product which should be added to the favourites list
  addProductToFaves(user: User): Observable<string> {
    return this.http.post(this.userUrl + '/addToFaves', user, {headers, responseType: 'text'});
  }

  // the userDto parameter should only contain the ID of the user
  // and one single element in the list of the favourite products,
  // containing the id of the product which should be removed from the favourites list
  removeProductFromFaves(user: User): Observable<string> {
    return this.http.post(this.userUrl + '/removeFromFaves', user, {headers, responseType: 'text'});
  }
}
