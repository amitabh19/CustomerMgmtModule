import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer } from '../customer';
import { Product } from '../product';
import { ThrowStmt } from '@angular/compiler';
import { Router } from '@angular/router';
@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  constructor(private _customerService:CustomerService, private router: Router) { }
    
  customer1 = new Customer;
  customerr = new Customer;
  cust = new Customer;
  products:Product[]=[];
  products1:Product[]=[];
  
  render : Boolean = false;
  ngOnInit() {
    this._customerService.getCustomerByIdBC().then((customer)=>{
      this.customer1 = customer;
      this.customerr = customer;
      this.cust = customer;
      console.log(customer);
    })
    console.log("yo");
    this._customerService.getProductsFromCart().then((product)=>{
      this.products = product;
      console.log(this.products);
    }).then((temp)=>
    {
      this.getData();
    }
    )
  }

  setRender()
  {
    this.render = true;
  }
  getData(){
    
    for(var t =0; t< this.customerr.customerCarts.length; t++){
      if(this.customer1.customerCarts[t].type=="wishlist"){
         this.customer1.customerCarts.splice(t,1);
         t=t-1;
      }
    }
    for(var ca of this.customer1.customerCarts){
      console.log(ca);
    }
    console.log("Final Cart");
    for(var ca of this.customerr.customerCarts){
      console.log(ca);
    }

    for(var c of this.customerr.customerCarts){
       for( var p of this.products)
      {
        if(c.productId==p.productId)
        {
          this.products1.push(p);
        }
      }
    }
    //console.log(this.customer1.customerCarts);
    this.setRender();
  }


  deleteFromCart(c){
    console.log(c);
    this._customerService.deleteFromCart(c.cartId).subscribe( temp=>
      {
        alert("Product deleted");
        window.location.reload();
      });

  }

  sendToWishlist(c,p){

    this.customer1.customerCarts.slice(c);

    this._customerService.sendToWishL(c.quantity,this.customer1,p).subscribe((cart)=>{
      console.log(cart);
    })

    this._customerService.deleteFromCart(c.cartId).subscribe((cart)=>{
      console.log(cart);
    })

    this.getData();
  }


}
