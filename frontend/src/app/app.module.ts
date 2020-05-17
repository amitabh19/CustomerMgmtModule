import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HttpModule } from '@angular/http';
import {ReactiveFormsModule,FormBuilder} from '@angular/forms';;
import { HomePageComponent } from './home-page/home-page.component'
import {Routes,RouterModule} from '@angular/router';;
import { ProductPageComponent } from './product-page/product-page.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';

const appRoutes:Routes =[
    {path:'showHome',component:HomePageComponent},
    {path:'showProduct',component:ProductPageComponent},
    {path:'',component:HomePageComponent},
]
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
        HttpModule,
        RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        AppComponent,
        HomePageComponent,
        ProductPageComponent ,
        CustomerCartComponent       
		],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule { }