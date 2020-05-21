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
  private customer:Customer;
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

 //send product to cart post
 addToCart(quantity:Number,customerId:Customer,productId:Product){
  console.log("JSON Object is "+[JSON.stringify(customerId),JSON.stringify(productId)]);
  let customerCart = {
    "quantity":quantity,
    "pid": productId.productId,
    "cid": customerId.userId
  }
  console.log(customerCart);
  return this._http.post(this.baseUrl+"/atC",customerCart,this.options).pipe(map((response: Response) => response.json()))
  .pipe(catchError(this.errorHandler));
}

//send product to wishlist post 
sendToWishL(quantity:Number,customerId:Customer,productId:Product){
  console.log("JSON Object is "+[JSON.stringify(customerId),JSON.stringify(productId)]);
  let customerCart = {
    "quantity":quantity,
    "pid": productId.productId,
    "cid": customerId.userId
  }
  console.log(customerCart);
  return this._http.post(this.baseUrl+"/atW",customerCart,this.options).pipe(map((response: Response) => response.json()))
  .pipe(catchError(this.errorHandler));
}


//delete from wishlist
sendToWishLTC(cartId:number){
  
  return this._http.delete(this.baseUrl+"/atDFW/"+cartId,this.options).pipe(map((response: Response) => response.json()))
  .pipe(catchError(this.errorHandler));
}

//delete from Cart
deleteFromCart(cartId:number){
  
  return this._http.delete(this.baseUrl+"/atDFC/"+cartId,this.options).pipe(map((response: Response) => response.json()))
  .pipe(catchError(this.errorHandler));
}

  //send product to cart put
  addToCartPut(customer:Customer){
    return this._http.put(this.baseUrl+"/atC",JSON.stringify(customer),this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }


  //get product by product id
  getProductById(productId:number){
    return this._http.get(this.baseUrl+"/product/"+productId,this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }


 //send product feedback
  create(ProductFeedback: Object,uId:number) {
    console.log(ProductFeedback.toString());
    return this._http.post(this.baseUrl+"/addFeedback/",JSON.stringify(ProductFeedback),this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  
  }

  //getter and setter functions for product (will be used to send product from home page to products page)
  setProduct(product:Product){
    this.product = product;
  }
  getProduct(){
    return this.product;
  }

  //getter and setter functions for customer
  setCustomer(customer:Customer){
    this.customer=customer;
  }
  getCustomer(){
    return this.customer;
  }

  //error handler 
  errorHandler(error:Response){
    return Observable.throw(error||"SERVER ERROR");  
  }

}
