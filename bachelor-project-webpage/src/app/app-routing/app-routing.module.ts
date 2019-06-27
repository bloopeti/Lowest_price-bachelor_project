import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {HomeComponent} from '../home/home.component';
import {ErrorComponent} from '../error/error.component';
import {LoginComponent} from '../login/login.component';
import {RegisterComponent} from '../register/register.component';
import {ProductComponent} from '../product/product.component';
import {FavouritesComponent} from '../favourites/favourites.component';
import {ProductDetailsComponent} from '../product-details/product-details.component';
import {AdminProductComponent} from '../admin-product/admin-product.component';
import {AdminProductUrlComponent} from '../admin-product-url/admin-product-url.component';


export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'error', component: ErrorComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'product/search/:searchQuery', component: ProductComponent},
  {path: 'favourites', component: FavouritesComponent},
  {path: 'productDetails/:id', component: ProductDetailsComponent},
  {path: 'admin/products', component: AdminProductComponent},
  {path: 'admin/productUrl/:id', component: AdminProductUrlComponent},
  {path: '**', redirectTo: 'error'}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
