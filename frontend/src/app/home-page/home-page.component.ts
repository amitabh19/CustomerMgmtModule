import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import {CustomerService} from '../customer.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  products:Product[];

  constructor(private _customerService:CustomerService ,private _router:Router) { }

  ngOnInit() {
    this._customerService.getAllProducts().subscribe((products)=>{
      console.log(products);
      this.products= products;
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
