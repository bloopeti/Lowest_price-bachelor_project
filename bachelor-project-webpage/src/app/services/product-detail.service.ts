import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductDetails} from '../model/productDetails';
import {IdWrapper} from '../model/idWrapper';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {

  productDetailsUrl = 'http://localhost:9906/app/productDetails';

  constructor(private http: HttpClient) {
  }

  getAllProductDetails(): Observable<ProductDetails[]> {
    return this.http.get<ProductDetails[]>(this.productDetailsUrl + '/getAll');
  }

  getProductDetailsById(id: number): Observable<ProductDetails> {
    return this.http.get<ProductDetails>(this.productDetailsUrl + '/get/' + id.toString());
  }

  addProductDetails(productDetails: ProductDetails): Observable<string> {
    return this.http.post(this.productDetailsUrl + '/add', productDetails, {headers, responseType: 'text'});
  }

  updateProductDetails(productDetails: ProductDetails): Observable<string> {
    // have to specify response type text if the http request returns a plain string
    return this.http.post(this.productDetailsUrl + '/update', productDetails, {headers, responseType: 'text'});
  }

  deleteProductDetails(id: IdWrapper): Observable<string> {
    return this.http.post(this.productDetailsUrl + '/delete', id, {headers, responseType: 'text'});
  }
}
