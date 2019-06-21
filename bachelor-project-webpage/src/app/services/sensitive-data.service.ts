import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class SensitiveDataService {

  private stringDataSource = new BehaviorSubject('dummy message');
  currentStringData = this.stringDataSource.asObservable();
  private numberDataSource = new BehaviorSubject(-1);
  currentNumberData = this.numberDataSource.asObservable();
  private productDataSource = new BehaviorSubject(new Product());
  currentProductData = this.productDataSource.asObservable();

  constructor() {
  }

  changeStringData(data: string) {
    this.stringDataSource.next(data);
  }

  changeNumberData(data: number) {
    this.numberDataSource.next(data);
  }

  changeProductData(data: Product) {
    this.productDataSource.next(data);
  }
}
