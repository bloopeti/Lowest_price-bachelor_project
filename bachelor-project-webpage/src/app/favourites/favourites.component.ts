import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {FavouriteProductService} from '../services/favourite-product.service';
import {Product} from '../model/product';
import {User} from '../model/user';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css', '../app.component.css']
})
export class FavouritesComponent implements OnInit {

  products: Product[] = [];
  requestComplete = false;
  displayedColumns: string[] = ['name', 'details'];
  productIsSelected: boolean;
  productSelected: Product;
  loggedInUserId: number;
  message: string;

  constructor(private router: Router,
              private userService: UserService, private favouriteProductService: FavouriteProductService) {
  }

  ngOnInit() {
    this.productIsSelected = false;
    this.loggedInUserId = parseInt(localStorage.getItem('loggedInUser'), 10);
    if (this.loggedInUserId === -1) {
      this.router.navigate(['error']);
    }
    this.userService.getUserById(this.loggedInUserId).subscribe(data => {
      this.products = data.favouriteProducts;
      this.requestComplete = true;
    });
    this.message = '';
  }

  viewDetails(product: Product): void {
    this.productIsSelected = true;
    this.productSelected = product;
  }

  removeFromFavourites(product: Product): void {
    const user = new User();
    user.id = this.loggedInUserId;
    user.favouriteProducts = [];
    const productToAdd = new Product();
    productToAdd.id = product.id;
    user.favouriteProducts.push(productToAdd);
    this.favouriteProductService.removeProductFromFaves(user).subscribe(data => {
      const response = data;
      if (response.includes('PRODUCT REMOVAL FROM FAVOURITES SUCCESSFUL')) {
        if (product === this.productSelected) {
          this.productSelected = new Product();
          this.productIsSelected = false;
        }
        this.requestComplete = false;
        this.userService.getUserById(this.loggedInUserId).subscribe(data2 => {
          this.products = data2.favouriteProducts;
          this.requestComplete = true;
        });
        this.message = 'Successfully removed product from your favourites!';
      } else if (response.includes('Product isn\'t favourited by this user')) {
        this.message = 'This product isn\'t in your favourites!';
      } else {
        this.message = 'An error happened!\nThe product wasn\'t added to your favourites!';
      }
    });
  }

}
