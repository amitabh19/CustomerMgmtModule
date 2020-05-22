import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-display-name',
  templateUrl: './name.component.html',
  styleUrls: ['./name.component.css']
})
export class DisplayByNameComponent implements OnInit {

  products:Product[];
  res=new Product();
  result:Product[];
  loc:string;
  searchText:string;
  result1:Product;
  rating:number;
  constructor(private _customerService:CustomerService,private router:Router) { }

  ngOnInit() {
    this._customerService.getProduct1().subscribe((data: any[]) => {
      console.log(data);
      this.products = data;

    })

        this.res=this._customerService.productGetter();
        
        this.loc=this.res.productImage;
        this.rating=this.res.productRating;

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
    if(this.result)
    {
      console.log("found");
        this._customerService.setPName=this.result1.productName;
        this._customerService.setter(this.result1);
        alert("Now taking u to product page");
        this.router.navigate(['disP']);
    }
  }
 
}
