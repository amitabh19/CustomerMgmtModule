import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import {CustomerService} from '../customer.service';
import { Customer } from '../customer';
import { Router } from '@angular/router';
@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent implements OnInit {

  product:Product;
  constructor(private _customerService:CustomerService, private router:Router) { }
  customer:Customer;
  location:String;
  success:Boolean=true;
  msg:String;
  ngOnInit() {
    
    this.product=this._customerService.getProduct()
    this.location= this.product.productImage;
    console.log("location is:"+this.location);
    console.log("Product receieved is:"+this.product);
    this._customerService.getCustomerById().subscribe((customer)=>{
      this.customer=customer;
      console.log(this.customer);
    })
  }

  sendToCart(quantity){
    
    let productJson = {
      "productId":this.product.productId,
      "productName":this.product.productName,
      "productImage":this.product.productImage,
      "productPrice":this.product.productPrice,
      "productRating":this.product.productRating,
      "noOfProductViewed":this.product.noOfProductViewed,
      "productBrand":this.product.productBrand,
      "noOfProducts":this.product.noOfProducts,
      "productInfo":this.product.productInfo,
      "discount":this.product.discount,
      "productCategory":this.product.productCategory,
      "productActivated":this.product.productActivated,
      "status":this.product.status,
      "featured":this.product.featured
    }
    let customerJson ={
      "userId":this.customer.userId,
      "name":this.customer.name,
      "username":this.customer.username,
      "password":this.customer.password,
      "eMail":this.customer.eMail,
      "role":this.customer.role,
      "securityQuestion":this.customer.securityQuestion,
      "securityAnswer":this.customer.securityAnswer,
      "phoneNumber":this.customer.phoneNumber,
      "alternatePhoneNumber":this.customer.alternatePhoneNumber,
      "alternateEmail":this.customer.alternateEmail,
      "address":this.customer.address,
      "active":this.customer.active,
      "customerCarts":this.customer.customerCarts
    }

    //send to cart service function
    this._customerService.addToCart(quantity,customerJson,productJson).subscribe((customer)=>{
      this.customer = customer;
      console.log(customer);
    });
    this._customerService.setCustomer(this.customer);
    this.success = false;
    this.msg="Item added to cart"
    //this.router.navigate(['showCart']);
    
  }

  goToCart()
  {
    this._customerService.setCustomer(this.customer);
    this.router.navigate(['showCart']);
  }

  goToWish()
  {
    this._customerService.setCustomer(this.customer);
    this.router.navigate(['showWishlist']);
  }

  sendToWishL(quantity){
    
    let productJson = {
      "productId":this.product.productId,
      "productName":this.product.productName,
      "productImage":this.product.productImage,
      "productPrice":this.product.productPrice,
      "productRating":this.product.productRating,
      "noOfProductViewed":this.product.noOfProductViewed,
      "productBrand":this.product.productBrand,
      "noOfProducts":this.product.noOfProducts,
      "productInfo":this.product.productInfo,
      "discount":this.product.discount,
      "productCategory":this.product.productCategory,
      "productActivated":this.product.productActivated,
      "status":this.product.status,
      "featured":this.product.featured
    }
    let customerJson ={
      "userId":this.customer.userId,
      "name":this.customer.name,
      "username":this.customer.username,
      "password":this.customer.password,
      "eMail":this.customer.eMail,
      "role":this.customer.role,
      "securityQuestion":this.customer.securityQuestion,
      "securityAnswer":this.customer.securityAnswer,
      "phoneNumber":this.customer.phoneNumber,
      "alternatePhoneNumber":this.customer.alternatePhoneNumber,
      "alternateEmail":this.customer.alternateEmail,
      "address":this.customer.address,
      "active":this.customer.active,
      "customerCarts":this.customer.customerCarts
    }

    //send to cart service function
    this._customerService.sendToWishL(quantity,customerJson,productJson).subscribe((customer)=>{
      console.log(customer);
    });
    this.success = false;
    this.msg="Item added to wishlist";
  }

  closeSpan(span){
    span.style.display="none";
  }

}
