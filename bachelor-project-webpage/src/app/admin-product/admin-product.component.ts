import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../model/product';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../services/product.service';
import {ProductDetails} from '../model/productDetails';
import {ProductUrl} from '../model/productUrl';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {

  products: Product[] = [];
  requestComplete = false;
  displayedColumns: string[] = ['name', 'details'];
  productIsSelected: boolean;
  @Input() productSelected: Product;
  message: string;
  isUserLogged: string;
  isUserAdmin: string;
  private sub: any;

  constructor(private router: Router, private route: ActivatedRoute,
              private productService: ProductService) {
  }

  ngOnInit() {
    this.productIsSelected = false;
    this.productSelected = new Product();
    this.productSelected.productDetails = new ProductDetails();
    this.productSelected.urls = [];
    this.getAllProducts();
    this.isUserLogged = localStorage.getItem('isUserLoggedIn');
    this.isUserAdmin = localStorage.getItem('isUserAdmin');
    this.message = '';
  }

  getAllProducts(): void {
    this.productService.getAllProduct()
      .subscribe(data => {
        this.products = data;
        this.requestComplete = true;
      });
  }

  viewDetails(product: Product): void {
    this.productIsSelected = true;
    this.productSelected = new Product();
    this.productSelected.id = product.id;
    this.productSelected.urls = product.urls;
    this.productSelected.name = product.name;
    this.productSelected.productDetails = product.productDetails;
  }

  add() {
    if (!this.productSelected.name) {
      return;
    }
    const productToAdd = new Product();
    productToAdd.name = this.productSelected.name.trim();
    this.productService.addProduct(productToAdd).subscribe();
    this.message = 'Product added';

    // this.getAllProducts();
  }

  update() {
    if (!this.productSelected.id) {
      return;
    }
    if (!this.productSelected.name) {
      return;
    }
    const productToUpdate = new Product();
    productToUpdate.id = this.productSelected.id;
    productToUpdate.name = this.productSelected.name.trim();
    this.productService.updateProduct(productToUpdate).subscribe();
    this.message = 'Product updated';

    // this.getAllProducts();
  }

  openFullDetailPage(productId: number): void {
    this.router.navigate(['productDetails', productId]);
  }

  manageProductUrls(productId: number): void {
    this.router.navigate(['admin/productUrl', productId]);
  }

}
