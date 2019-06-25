import {Component, OnInit} from '@angular/core';
import {Product} from '../model/product';
import {ProductService} from '../services/product.service';
import {SensitiveDataService} from '../services/sensitive-data.service';
import {User} from '../model/user';
import {FavouriteProductService} from '../services/favourite-product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductUrl} from '../model/productUrl';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css', '../app.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product();
  productId: number;
  displayedColumns: string[] = ['domain', 'url', 'price'];
  isUserLogged: string;
  message: string;

  constructor(private route: ActivatedRoute,
              private productService: ProductService, private sensitiveDataService: SensitiveDataService,
              private favouriteProductService: FavouriteProductService) {
  }

  ngOnInit() {
    this.sensitiveDataService.currentNumberData.subscribe(message => this.productId = message);
    this.sensitiveDataService.changeNumberData(-1); // reset the data
    this.route.params.subscribe(params => {
      this.productId = params['id'];
      this.productService.getProductById(this.productId).subscribe(data => {
        this.product = data;
        console.log(data);
      });
    });
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.message = '';
  }

  addToFavourites(product: Product): void {
    const user = new User();
    user.id = parseInt(localStorage.getItem('loggedInUser'), 10);
    user.favouriteProducts = [];
    const productToAdd = new Product();
    productToAdd.id = product.id;
    user.favouriteProducts.push(productToAdd);
    this.favouriteProductService.addProductToFaves(user).subscribe(data => {
      const response = data;
      if (response.includes('PRODUCT INSERTION TO FAVOURITES SUCCESSFUL')) {
        this.message = 'Successfully added product to your favourites!';
      } else if (response.includes('This product is already in the favourites')) {
        this.message = 'This product is already in your favourites!';
      } else {
        this.message = 'An error happened!\nThe product wasn\'t added to your favourites!';
      }
    });
  }
}
