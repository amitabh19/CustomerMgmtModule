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
  products1:Product[];
  searchText:string;
  result:Product[];
  customer:Customer;
  names=new Array;

  constructor(private _customerService:CustomerService,private _router:Router) { }

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

  go()
  {
    this._router.navigate(['/cat']);
  }

  showMenu(menu){
    menu.style.display="block";  
  }
  closeMenu(menu){
    menu.style.display="none";
    
  }

  search()
  {
    this.result=this.products.filter(res=>
      {
        return res.productName.toLocaleLowerCase().match(this.searchText.toLocaleLowerCase());
      });
    if(this.result)
    {
      console.log("found");
      this._customerService.setP = this.products1;
      this._customerService.setPName =this.searchText;
      alert("Now taking u to product page");
      this._router.navigate(['disP']);
    }
  }

  sendProduct(p){
    this._customerService.setProduct(p);
    this._router.navigate(['/showProduct']);
  }

}
