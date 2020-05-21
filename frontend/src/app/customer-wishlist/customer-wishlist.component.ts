import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer } from '../customer';
import { Product } from '../product';
@Component({
  selector: 'app-customer-wishlist',
  templateUrl: './customer-wishlist.component.html',
  styleUrls: ['./customer-wishlist.component.css']
})
export class CustomerWishlistComponent implements OnInit {

  constructor(private _customerService:CustomerService) { }
  customer:Customer;
  customer1:Customer
  products:Product[]=[];
  product:Product;
  ngOnInit() {
    console.log(this._customerService.getCustomer());
    this.customer=this._customerService.getCustomer();
   this.customer1 = this.customer;
    console.log("The customer getter is: "+this.customer1)

    console.log("initial wishlist")
   
    for(var ca of this.customer1.customerCarts){
      console.log(ca);
    }

    for(var c =0;c< this.customer1.customerCarts.length;c++){
      if(this.customer1.customerCarts[c].type=="cart"){
         this.customer1.customerCarts.splice(c,1);
         c=c-1;
      }
    }
    console.log("Final wishlist");
    for(var ca of this.customer1.customerCarts){
      console.log(ca);
    }

    for(var p of this.customer1.customerCarts){
      this._customerService.getProductById(Number(p.productId)).subscribe(
        (product)=>{
          //console.log(product);
          this.products.push(product);
        }
      )
    }
    console.log(this.products);

    this._customerService.setCustomer(this.customer);
  }

  sendToCart(c,p:Product){
   console.log(c.productId);
   console.log(c.quantity);
    console.log(this.customer.userId);

    this._customerService.addToCart(c.quantity,this.customer,p).subscribe((customer)=>{
      console.log(customer);
    })
  
   this._customerService.sendToWishLTC(c.cartId).subscribe((customer)=>{
     console.log(customer);
   })
  }

  deleteFromWishList(c){
    this._customerService.sendToWishLTC(c.cartId).subscribe((customer)=>{
      console.log(customer);
    })
  }

}
