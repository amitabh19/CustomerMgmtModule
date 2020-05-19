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
  constructor(private customerService:CustomerService,private router:Router) { }

  ngOnInit() {
    this.customerService.getProduct().subscribe((data: any[]) => {
      console.log(data);
      this.products = data;

    })

        this.res=this.customerService.productGetter();
        
        this.loc=this.res.productImage;
        this.rating=this.res.productRating;

  }

  Cart()
  {
      alert("taking u to the cart");
      this.router.navigate(['cart']);
  }

  wishList()
  {
      alert("taking u to your wishlist");
      this.router.navigate(['wishList']);
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
