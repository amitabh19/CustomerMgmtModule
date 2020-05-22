import { Component, OnInit, Input } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Customer} from '../Customer';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  private customerDetails:Customer;

  constructor(private _customerService:CustomerService) { }

  ngOnInit() {
    this._customerService.getCustomerByIdBC().then((customer)=>{
      this.customerDetails = customer;

      console.log(this.customerDetails);
    })

  }

}
