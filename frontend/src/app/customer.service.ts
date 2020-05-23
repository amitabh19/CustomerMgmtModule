import { Injectable } from '@angular/core';
import {Http,Response,Headers,RequestOptions} from '@angular/http';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators'; 
import { Observable, throwError, from } from 'rxjs';
import { Product } from './product';
import {Customer} from './customer';
import { ProductFeedback1 } from './productFeedback1';
import { CommonFeedback1 } from './commonFeedback1';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl:String = 'http://localhost:8082/customer';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});
  private product:Product;
  private customer:Customer;
  public setPName=new String;
  public setP : Product[];
 
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

  getCustomerByIdBC(){
    return this._http.get(this.baseUrl+"/customer/3",this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler)).toPromise();
  }

  //get product by customer id
  getProductsFromCart(){
    return this._http.get(this.baseUrl+"/cartProducts/3",this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler)).toPromise();
  }

  getProductsFromWishlist()
  {
    return this._http.get(this.baseUrl+"/wishProducts/3",this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler)).toPromise();
  }

 //send product to cart post
  addToCart(quantity:Number,customer:Customer,product:Product){
  //console.log("JSON Object is "+[JSON.stringify(customer),JSON.stringify(product)]);
  let customerCart = {
    "quantity":quantity,
    "pid": product,
    "cid": customer.userId
  }
  console.log(customerCart);
  return this._http.post(this.baseUrl+"/atC",JSON.stringify(customerCart),this.options).pipe(map((response: Response) => response.json()))
  .pipe(catchError(this.errorHandler));
}

//send product to wishlist post 
sendToWishL(quantity:Number,customerId:Customer,productId:Product){
  let customerCart = {
    "quantity":quantity,
    "pid": productId,
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
  console.log(cartId);
  return this._http.delete(this.baseUrl+"/atDFC/"+cartId,this.options).pipe(catchError(this.errorHandler));
}

  //send product to cart put
  addToCartPut(customer:Customer){
    return this._http.put(this.baseUrl+"/atC",JSON.stringify(customer),this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }

  //update customer
  updateCustomer(customer: Customer)
  {
    return this._http.put(this.baseUrl+"/updateCustomerDetails",JSON.stringify(customer),this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }

  //get product by product id
  getProductById(productId:number){
    return this._http.get(this.baseUrl+"/product/"+productId,this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }


 
 //send product feedback
 create(productFeedback: ProductFeedback1) { 
  return this._http.post(this.baseUrl+"/addFeedback/",productFeedback).pipe(catchError(this.errorHandler));
}

//send common feedback
 createCommonFeedback(commonFeedback: CommonFeedback1) { 
  return this._http.post(this.baseUrl+"/addCommonFeedback/",commonFeedback).pipe(catchError(this.errorHandler));
}

//to get product name of order by customer
getOrderedProductName(id: number){
  return this._http.get(this.baseUrl+"/orderedProductName/"+id,this.options).pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));
}

//get product id by product name
getProductIdByName(name: string){
  return this._http.get(this.baseUrl+"/productIdByName/"+name,this.options).pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));
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
  
  getProduct1():Observable<Product[]>
  {
      return this._http.get(this.baseUrl+'/products').pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));
  }

  getProductByName(name:string)
  {
      return this._http.get(this.baseUrl+'/product/'+name).pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));;
  }

  getProductByCategory(category:string)
  {
      return this._http.get(this.baseUrl+'/categories/'+category).pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));;
  }

  getProducts()
  {
    return this._http.get(this.baseUrl+'/products').pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));
  }

  productGetter()
  {
    return this.product;
  }
   setter(product:Product)
   {
     this.product=product;
   }

   getCategory()
   {
     return this._http.get(this.baseUrl+'/categories').pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler));
   }

   getName()
   {
    return this._http.get(this.baseUrl+'/productsName').pipe(map((response: Response) => response.json())).pipe(catchError(this.errorHandler)); 
   }

}
