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
  customerr:Customer;
  customer1:Customer
  products:Product[]=[];
  product:Product;
  products1:Product[]=[];
  render : Boolean = false;

  ngOnInit() {
    this._customerService.getCustomerByIdBC().then((customer)=>{
      this.customer1 = customer;
      this.customerr = customer;
      console.log(customer);
    })
    console.log("yo");
    this._customerService.getProductsFromWishlist().then((product)=>{
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
  getData()
  {
    for(var t =0; t< this.customerr.customerCarts.length; t++){
      if(this.customer1.customerCarts[t].type=="cart"){
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
    this.setRender();

  }

  sendToCart(c,p:Product){
   console.log(c.productId);
   console.log(c.quantity);
    console.log(this.customerr.userId);

    this._customerService.addToCart(c.quantity,this.customerr,p).subscribe((customer)=>{
      console.log(customer);
    });
    alert("Product added to cart");
    this.deleteFromWishList(c);
    
   
  }

  deleteFromWishList(c){
    console.log(c);
    this._customerService.deleteFromCart(c.cartId).subscribe( temp=>
      {
        alert("Product deleted from wishlist");
        window.location.reload();
      });

  }

}
