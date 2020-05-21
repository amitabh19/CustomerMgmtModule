import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer } from '../customer';
import { Product } from '../product';
import { ThrowStmt } from '@angular/compiler';
@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  constructor(private _customerService:CustomerService) { }
  customer:Customer;
  customer1:Customer;
  products:Product[]=[];
  dataRefresher:any;
  ngOnInit() {
    this.getData(true);
    this.refreshData();   
  }

  getData(setPageFlag){

    console.log(this._customerService.getCustomer());
    this.customer=this._customerService.getCustomer();
    this.customer1=this.customer;
    console.log("The customer getter is: "+this.customer1)

    console.log("initial cart")
   
    for(var ca of this.customer1.customerCarts){
      console.log(ca);
    }

    for(var c =0;c< this.customer1.customerCarts.length;c++){
      if(this.customer1.customerCarts[c].type=="wishlist"){
         this.customer1.customerCarts.splice(c,1);
         c=c-1;
      }
    }
    console.log("Final Cart");
    for(var ca of this.customer1.customerCarts){
      console.log(ca);
    }

    for(var p of this.customer.customerCarts){
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

  refreshData(){
    this.dataRefresher =
      setInterval(() => {
        //this.getData(false);
        //Passing the false flag would prevent page reset to 1 and hinder user interaction
      }, 30000);  
  }

  deleteFromCart(c){

    this._customerService.deleteFromCart(c.cartId).subscribe((cart)=>{
      console.log(cart);
    })
  }

  sendToWishlist(c,p){

    this.customer1.customerCarts.slice(c);

    this._customerService.sendToWishL(c.quantity,this.customer,p).subscribe((cart)=>{
      console.log(cart);
    })

    this._customerService.deleteFromCart(c.cartId).subscribe((cart)=>{
      console.log(cart);
    })

    this.getData(true);
    this.refreshData(); 
  }


}
