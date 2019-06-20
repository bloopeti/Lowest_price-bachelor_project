import {Injectable} from '@angular/core';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {IdWrapper} from '../model/idWrapper';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  userUrl = 'http://localhost:9906/app/user';

  constructor(private http: HttpClient) {
  }

  attemptRegister(user: User): Observable<string> {
    return this.http.post(this.userUrl + '/register', user, {headers, responseType: 'text'});
  }
}
