import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {IdWrapper} from '../model/idWrapper';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl = 'http://localhost:9906/app/user';

  constructor(private http: HttpClient) {
  }

  getAllUser(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl + '/getAll');
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.userUrl + '/get/' + id.toString());
  }

  addUser(user: User): Observable<string> {
    return this.http.post(this.userUrl + '/add', user, {headers, responseType: 'text'});
  }

  updateUser(user: User): Observable<string> {
    return this.http.post(this.userUrl + '/update', user, {headers, responseType: 'text'});
  }

  deleteUser(id: IdWrapper): Observable<string> {
    return this.http.post(this.userUrl + '/delete', id, {headers, responseType: 'text'});
  }
}
