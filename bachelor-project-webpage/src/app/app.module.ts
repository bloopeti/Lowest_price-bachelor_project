import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {MatIconModule, MatMenuModule, MatToolbarModule, MatButtonModule, MatTableModule, MatFormFieldModule} from '@angular/material';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';


import {AppRoutingModule} from './app-routing/app-routing.module';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {ErrorComponent} from './error/error.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ProductComponent} from './product/product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { FavouritesComponent } from './favourites/favourites.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ErrorComponent,
    LoginComponent,
    RegisterComponent,
    ProductComponent,
    ProductDetailsComponent,
    FavouritesComponent
  ],
  imports: [
    BrowserModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
