import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Customer } from '../customer';

@Component({
  selector: 'app-category',
  templateUrl: './categoryD.component.html',
  styleUrls: ['./categoryD.component.css']
})
export class CategoryDComponent implements OnInit {

  products:Product[];
  products1:Product[];
  searchText:string;
  result:Product[];
  disp=new Product();
  result1:Product;
  customer:Customer;
  constructor(private _customerService:CustomerService,private router:Router) { }

  ngOnInit() {

   this.products1= this._customerService.setP;

   this._customerService.getProduct1().subscribe((data:any[])=>
   {
     console.log(data);
     this.products=data;
   })
  }

  
  Cart(p:Product)
  {
    this._customerService.setProduct(p);
    this.router.navigate(['/showProduct']);
  }

  wishList(p:Product)
  {
    this._customerService.setProduct(p);
    this.router.navigate(['/showProduct']);
  }

  go()
  {
    this.router.navigate(['/cat']);
  }

  search()
  {
    this.result1=this.products.find(x=>x.productName==this.searchText);
    if(this.result1)
    {
      console.log("found");
        this._customerService.setPName=this.result1.productName;
        this._customerService.setter(this.result1);
        alert("Now taking u to product page");
        this.router.navigate(['disP']);
    }
  }
  
}
