import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../model/product';
import {ProductUrl} from '../model/productUrl';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {ProductUrlService} from '../services/product-url.service';

@Component({
  selector: 'app-admin-product-url',
  templateUrl: './admin-product-url.component.html',
  styleUrls: ['./admin-product-url.component.css']
})
export class AdminProductUrlComponent implements OnInit {

  productId: number;
  product: Product;
  requestComplete = false;
  displayedColumns: string[] = ['url', 'domain', 'details'];
  urlIsSelected: boolean;
  @Input() urlSelected: ProductUrl;
  message: string;
  isUserLogged: string;
  isUserAdmin: string;
  private sub: any;

  constructor(private router: Router, private route: ActivatedRoute,
              private productService: ProductService, private productUrlService: ProductUrlService) {
  }

  ngOnInit() {
    this.urlSelected = new ProductUrl();
    this.urlIsSelected = false;
    this.route.params.subscribe(params => {
      this.productId = params['id'];
      this.productService.getProductById(this.productId).subscribe(data => {
        this.product = data;
        console.log(data);
      });
    });
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    this.message = '';
  }

  refreshProduct(): void {
    this.productService.getProductById(this.productId).subscribe(data => {
      this.product = data;
      console.log(data);
    });
  }

  viewDetails(url: ProductUrl): void {
    this.urlIsSelected = true;
    this.urlSelected = new ProductUrl();
    this.urlSelected.id = url.id;
    this.urlSelected.url = url.url;
    this.urlSelected.domain = url.domain;
    this.urlSelected.productId = url.productId;
  }

  add() {
    if (!this.urlSelected.domain) {
      return;
    }
    if (!this.urlSelected.url) {
      return;
    }
    const urlToAdd = new ProductUrl();
    urlToAdd.domain = this.urlSelected.domain.trim();
    urlToAdd.url = this.urlSelected.url.trim();
    urlToAdd.productId = this.urlSelected.productId;
    this.productUrlService.addProductUrl(urlToAdd).subscribe();
    this.message = 'URL added to product';

    // this.refreshProduct();
  }

  update() {
    if (!this.urlSelected.id) {
      return;
    }
    if (!this.urlSelected.domain) {
      return;
    }
    if (!this.urlSelected.url) {
      return;
    }
    const urlToUpdate = new ProductUrl();
    urlToUpdate.id = this.urlSelected.id;
    urlToUpdate.domain = this.urlSelected.domain.trim();
    urlToUpdate.url = this.urlSelected.url.trim();
    this.productUrlService.updateProductUrl(urlToUpdate).subscribe();
    this.message = 'Product URL updated';

    // this.refreshProduct();
  }

}
