import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import {Router} from '@angular/router';
import { Customer } from '../Customer';

@Component({
  selector: 'app-update-customer-details',
  templateUrl: './update-customer-details.component.html',
  styleUrls: ['./update-customer-details.component.css']
})
export class UpdateCustomerDetailsComponent implements OnInit {

  customerDetails:Customer;

  constructor(private _customerService:CustomerService,private _router:Router) { }

  ngOnInit() {
    this._customerService.getCustomerById().subscribe(customerDetails =>this.customerDetails=customerDetails);
    console.log(this.customerDetails);
  }

  updateDetails()
  {
    this._customerService.updateCustomerDetails(this.customerDetails).subscribe((customerDetails)=>{
      console.log(customerDetails);
    },(error)=>{
      console.log(error);
    })
    this._router.navigate(['/showCustomerDetails'])
  }

}
