import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer } from '../customer';
import { Product } from '../product';
@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  constructor(private _customerService:CustomerService) { }
  customer:Customer;
  products:Product[]=[];
 
  ngOnInit() {
    console.log(this._customerService.getCustomer());
    this.customer=this._customerService.getCustomer();
    console.log("The customer getter is: "+this.customer)

    for(var c =0;c< this.customer.customerCarts.length;c++){
      if(this.customer.customerCarts[c].type=='wishlist'){
         this.customer.customerCarts.splice(c,1);
      }
    }
    console.log("Final cart");
    for(var ca of this.customer.customerCarts){
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
  }

}
