import { Injectable } from '@angular/core';
import {Http,Response,Headers,RequestOptions} from '@angular/http';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators'; 
import { Observable, throwError, from } from 'rxjs';
import { Product } from './product';
import {Customer} from './customer';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl:String = 'http://localhost:8082/customer';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});
  private product:Product;
  constructor(private _http:Http) { }

  //get product list
  getAllProducts(){
    return this._http.get(this.baseUrl+"/products",this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }

  //get customer by id
  getCustomerById(){
    return this._http.get(this.baseUrl+"/customer/3",this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }


  //send product to cart
  addToCart(quantity:Number,customerId:Customer,productId:Product){
    console.log("JSON Object is "+JSON.stringify(productId));
    return this._http.post(this.baseUrl+"/atC",[JSON.stringify(customerId),JSON.stringify(productId)],this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }

  //getter and setter functions for product (will be used to send product from home page to products page)
  setProduct(product:Product){
    this.product = product;
  }
  getProduct(){
    return this.product;
  }
  //error handler 
  errorHandler(error:Response){
    return Observable.throw(error||"SERVER ERROR");  
  }

}
