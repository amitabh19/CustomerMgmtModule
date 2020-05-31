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

  
  customerr:Customer;
  customer1:Customer
  cust = new Customer();
  products:Product[]=[];
  product:Product;
  products1:Product[]=[];
  render : Boolean = false;

  constructor(private _customerService:CustomerService) { }

  ngOnInit() {
    this._customerService.getCustomerByIdBC().then((customer)=>{
      this.customer1 = customer;
      this.customerr = customer;
      console.log(customer);
    }).then( (t)=>
    {
    this._customerService.getProductsFromWishlist().then((product)=>{
      this.products = product;
      console.log(this.products);
    }).then((temp)=>
    {
      this.getData();
    }
    )}
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
    
    console.log( this.customer1.customerCarts)
    for(var c of this.customer1.customerCarts){
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

    console.log(c.cartId, p.productId);
    let cust:Customer;
    this._customerService.getCustomerByIdBC().then((customer)=>{
     cust = customer;
    }).then( (t)=>{
    this._customerService.addToCart(c.quantity,cust,p).subscribe((customer)=>{
    })
    
  }).then(
    (y)=>{
      this.deleteFromWishList(c);
    }
  )
   
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
