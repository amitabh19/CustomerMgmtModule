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

  products: Product[];
  res = new Product();
  result: Product[];
  loc: String;
  searchText: String;
  result1: Product;
  rating: Number;
  constructor(private _customerService: CustomerService, private router: Router) { }

  ngOnInit() {
    this.result = this._customerService.setP;
    this.searchText = this._customerService.setPName;
    console.log(this.result);
    //this.res = this._customerService.productGetter();

    //this.loc = this.res.productImage;
    //this.rating = this.res.productRating;

  }

  Cart(p: Product) {
    this._customerService.setProduct(p);
    this.router.navigate(['/showProduct']);
  }

  wishList(p: Product) {
    this._customerService.setProduct(p);
    this.router.navigate(['/showProduct']);
  }

  go() {
    this.router.navigate(['/cat']);
  }


}
