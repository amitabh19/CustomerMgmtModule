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
import { CustomerWishlistComponent } from './customer-wishlist/customer-wishlist.component';
import {ProductFeedbackComponent} from './product-feedback/product-feedback.component';
import {CustomerDetailsComponent} from './customer-details/customer-details.component';
import { DisplayByNameComponent } from './display_byName/name.component';
import { CategoryComponent } from './category_wise/category.component';
import { CategoryDComponent } from './display_byCategory/categoryD.component';
import { FilterPipe } from './filter.pipe';
import { CommonFeedbackComponent } from './common-feedback/common-feedback.component';
import {UpdateCustomerDetailsComponent} from './update-customer-details/update-customer-details.component';

const appRoutes:Routes =[
	{path:'',component:HomePageComponent},
    {path:'showHome',component:HomePageComponent},
    {path:'showProductFeedbackForm',component:ProductFeedbackComponent},
    {path:'disP',component:DisplayByNameComponent},
    {path:'cat',component:CategoryComponent},
    {path:'disC',component:CategoryDComponent},
    {path:'showProduct',component:ProductPageComponent},
    {path:'showCart',component:CustomerCartComponent},
    {path:'showWishlist',component:CustomerWishlistComponent},
    {path:'showProductFeedback',component:ProductFeedbackComponent},
    {path:'showCommonFeedback', component:CommonFeedbackComponent},
    {path:'showCustomerDetails',component:CustomerDetailsComponent},
    {path:'updateCustomerDetails',component:UpdateCustomerDetailsComponent}
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
        HomePageComponent,DisplayByNameComponent,CategoryComponent,CategoryDComponent,FilterPipe,
        ProductPageComponent ,
        CustomerCartComponent ,
        CustomerWishlistComponent ,
        ProductFeedbackComponent,
        CommonFeedbackComponent,
        CustomerDetailsComponent ,
        UpdateCustomerDetailsComponent    
		],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule { }