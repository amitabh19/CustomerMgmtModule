import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  products:Product[];
  products1:Product[];
  searchText:string;
  result:Product[];
  disp=new Product();
  constructor(private customerService:CustomerService,private router:Router) { }

  ngOnInit() {
    this.customerService.getCategory().subscribe((products)=>
    {
        console.log(products);
        this.products=products;
    })

    this.customerService.getProducts().subscribe((data:any[])=>
    {
        console.log(data);
        this.products1=data;
    })
  }

  display(p:string)
  {
    this.customerService.getProductByCategory(p).subscribe((result)=>
    {
        console.log(result);
        this.result=result;
    })
    if(this.result!=null)
    {
        this.customerService.setP=this.result;
        alert('Displaying products');
        this.router.navigate(['/disC']);
    }
  }

  go()
  {
    this.router.navigate(['/cat']);
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
      this.customerService.setP = this.products1;
      this.customerService.setPName =this.searchText;
      alert("Now taking u to product page");
      this.router.navigate(['disP']);
    }
  }
  
}
