import {Component, OnInit} from '@angular/core';
import {Product} from '../model/product';
import {ProductService} from '../services/product.service';
import {SensitiveDataService} from '../services/sensitive-data.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css', '../app.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product;
  productId: number;
  isUserLogged: string;

  constructor(private productService: ProductService, private sensitiveDataService: SensitiveDataService) {
  }

  ngOnInit() {
    this.sensitiveDataService.currentNumberData.subscribe(message => this.productId = message);
    this.sensitiveDataService.changeNumberData(-1); // reset the data
    this.productService.getProductById(this.productId).subscribe(data => {
      this.product = data;
    });
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
  }

}
