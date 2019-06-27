import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {SensitiveDataService} from '../services/sensitive-data.service';
import {FavouriteProductService} from '../services/favourite-product.service';
import {Product} from '../model/product';
import {User} from '../model/user';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css', '../app.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[] = [];
  requestComplete = false;
  displayedColumns: string[] = ['name', 'details'];
  searchQueryParam: string;
  searchQuery: string;
  productIsSelected: boolean;
  productSelected: Product;
  private sub: any;
  isUserLogged: string;
  isUserAdmin: string;
  message: string;

  constructor(private router: Router, private route: ActivatedRoute,
              private productService: ProductService, private favouriteProductService: FavouriteProductService) {
  }

  ngOnInit() {
    this.productIsSelected = false;
    this.sub = this.route.params.subscribe(params => {
      this.searchQueryParam = params['searchQuery'];
      this.productService.getProductsBySearchQuery(this.searchQueryParam)
        .subscribe(data => {
          this.products = data;
          this.requestComplete = true;
        });
    });
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    this.message = '';
  }

  viewDetails(product: Product): void {
    this.productIsSelected = true;
    this.productSelected = product;
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

  search(query: string): void {
    this.router.navigate(['product/search', query]);
  }

  openFullDetailPage(productId: number): void {
    this.router.navigate(['productDetails', productId]);
  }

}
