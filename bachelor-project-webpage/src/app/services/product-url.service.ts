import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductUrl} from '../model/productUrl';
import {IdWrapper} from '../model/idWrapper';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class ProductUrlService {

  productUrlUrl = 'http://localhost:9906/app/productUrl';

  constructor(private http: HttpClient) {
  }

  getAllProductUrl(): Observable<ProductUrl[]> {
    return this.http.get<ProductUrl[]>(this.productUrlUrl + '/getAll');
  }

  getProductUrlById(id: number): Observable<ProductUrl> {
    return this.http.get<ProductUrl>(this.productUrlUrl + '/get/' + id.toString());
  }

  addProductUrl(productUrl: ProductUrl): Observable<string> {
    return this.http.post(this.productUrlUrl + '/add', productUrl, {headers, responseType: 'text'});
  }

  updateProductUrl(productUrl: ProductUrl): Observable<string> {
    return this.http.post(this.productUrlUrl + '/update', productUrl, {headers, responseType: 'text'});
  }

  deleteProductUrl(id: IdWrapper): Observable<string> {
    return this.http.post(this.productUrlUrl + '/delete', id, {headers, responseType: 'text'});
  }
}
