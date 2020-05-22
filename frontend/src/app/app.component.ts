import { Component } from '@angular/core';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls:['app.component.css']
})

export class AppComponent {
    message="Sent From Parent";
    result="";
products:Product[];
    names=new Array;
    result1:Product;
    searchText:string;
    constructor(private customerService:CustomerService,private router:Router) { }

  ngOnInit() {
    this.customerService.getProduct1().subscribe((data: any[]) => {
      console.log(data);
      this.products = data;

    })

    this.customerService.getName().subscribe((data:any[])=>
    {
      console.log(data);
      this.names=data;
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
    this.result1=this.products.find(x=>x.productName==this.searchText);
    if(this.result1)
    {
      console.log("found");
        this.customerService.setPName=this.result1.productName;
        this.customerService.setter(this.result1);
        alert("Now taking u to product page");
        this.router.navigate(['disP']);
    }
  }


 }