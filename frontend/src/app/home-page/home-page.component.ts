import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import {CustomerService} from '../customer.service';
import {Router} from '@angular/router';
import { Customer } from '../customer';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products:Product[];
  customer:Customer;
  constructor(private _customerService:CustomerService ,private _router:Router) { }

  ngOnInit() {
    this._customerService.getAllProducts().subscribe((products)=>{
      console.log(products);
      this.products= products;
    })

    this._customerService.getCustomerById().subscribe((customer)=>{
      this.customer=customer;
      this._customerService.setCustomer(this.customer);
      
    })

  }
  showMenu(menu){
    menu.style.display="block";  
  }
  closeMenu(menu){
    menu.style.display="none";
  }
  sendProduct(p){
    this._customerService.setProduct(p);
    this._router.navigate(['/showProduct']);
  }

}
