import { Component } from '@angular/core';
import { Product } from './product';
import { CustomerService } from './customer.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls:['app.component.css']
})

export class AppComponent {
    message="Sent From Parent";
    result="";
  
    searchText:string;

    products:Product[];
    products1:Product[];
    
    constructor(private customerService:CustomerService,private router:Router) { }

  ngOnInit() {
    this.customerService.getAllProducts().subscribe((data: any[]) => {
      console.log(data);
      this.products = data;

    })

  }
    showMenu(menu){
        menu.style.display="block";  
      }
      closeMenu(menu){
        menu.style.display="none";
      }

   go()
  {
    this.router.navigate(['/cat']);
  }

  search()
  {
    //this.result1=this.products.find(x=>x.productName==this.searchText);
    this.products1=this.products.filter(res=>
      {
        return res.productName.toLocaleLowerCase().match(this.searchText.toLocaleLowerCase());
      });
    
    if(this.products1)
    {
      console.log(this.products1);

      //this.customerService.setPName= String(this.products1[0].productName);
        //this.customerService.setter(this.result1);
        this.customerService.setP = this.products1;
        this.customerService.setPName =this.searchText;
        alert("Now taking u to product page");
        this.router.navigate(['disP']);
        
    }
  }


 }