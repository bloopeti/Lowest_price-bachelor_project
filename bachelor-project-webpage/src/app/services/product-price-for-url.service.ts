import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductPriceForUrl} from '../model/productPriceForUrl';
import {IdWrapper} from '../model/idWrapper';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class ProductPriceForUrlService {

  productPriceForUrlUrl = 'http://localhost:9906/app/productPriceForUrl';

  constructor(private http: HttpClient) {
  }

  getAllProductPriceForUrl(): Observable<ProductPriceForUrl[]> {
    return this.http.get<ProductPriceForUrl[]>(this.productPriceForUrlUrl + '/getAll');
  }

  getProductPriceForUrlById(id: number): Observable<ProductPriceForUrl> {
    return this.http.get<ProductPriceForUrl>(this.productPriceForUrlUrl + '/get/' + id.toString());
  }

  addProductPriceForUrl(productPriceForUrl: ProductPriceForUrl): Observable<string> {
    return this.http.post(this.productPriceForUrlUrl + '/add', productPriceForUrl, {headers, responseType: 'text'});
  }

  updateProductPriceForUrl(productPriceForUrl: ProductPriceForUrl): Observable<string> {
    return this.http.post(this.productPriceForUrlUrl + '/update', productPriceForUrl, {headers, responseType: 'text'});
  }

  deleteProductPriceForUrl(id: IdWrapper): Observable<string> {
    return this.http.post(this.productPriceForUrlUrl + '/delete', id, {headers, responseType: 'text'});
  }
}
