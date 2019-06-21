import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/product';
import {IdWrapper} from '../model/idWrapper';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productUrl = 'http://localhost:9906/app/product';

  constructor(private http: HttpClient) {
  }

  getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl + '/getAll');
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(this.productUrl + '/get/' + id.toString());
  }

  addProduct(product: Product): Observable<string> {
    return this.http.post(this.productUrl + '/add', product, {headers, responseType: 'text'});
  }

  updateProduct(product: Product): Observable<string> {
    return this.http.post(this.productUrl + '/update', product, {headers, responseType: 'text'});
  }

  deleteProduct(id: IdWrapper): Observable<string> {
    return this.http.post(this.productUrl + '/delete', id, {headers, responseType: 'text'});
  }

  getProductsBySearchQuery(searchQuery: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl + '/search/' + searchQuery);
  }
}
