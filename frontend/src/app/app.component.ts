import { Component } from '@angular/core';
import { Customer } from 'src/app/customer';
import { CustomerService } from './customer.service';
@Component({
  selector: 'app',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})

export class AppComponent {

  message = "Sent From Parent";
  result = "";


  showMenu(menu) {
    menu.style.display = "block";
  }
  closeMenu(menu) {
    menu.style.display = "none";
  }
 }