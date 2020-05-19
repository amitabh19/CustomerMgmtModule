import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

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
  constructor(private customerService:CustomerService,private router:Router) { }

  ngOnInit() {

   this.products1= this.customerService.setP;

   this.customerService.getProduct().subscribe((data:any[])=>
   {
     console.log(data);
     this.products=data;
   })
  }

  
  Cart()
  {
      this.router.navigate(['/cart']);
  }

  wishList()
  {
      this.router.navigate(['/wish']);
  }

  go()
  {
    this.router.navigate(['/cat']);
  }

  search()
  {
    this.result1=this.products.find(x=>x.productName==this.searchText);
    if(this.result)
    {
      console.log("found");
        this.customerService.setPName=this.result1.productName;
        this.customerService.setter(this.result1);
        alert("Now taking u to product page");
        this.router.navigate(['disP']);
    }
  }
  
}
