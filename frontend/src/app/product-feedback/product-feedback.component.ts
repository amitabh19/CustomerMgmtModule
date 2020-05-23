import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-feedback',
  templateUrl: './product-feedback.component.html',
  styleUrls: ['./product-feedback.component.css']
})
export class ProductFeedbackComponent implements OnInit {

  submitted: boolean=false;
  submitted1: boolean=false;
  ProductFeedbackForm: FormGroup;
  message:any;
  check:boolean=false; 
  

  constructor(private formBuilder: FormBuilder, private customerService:CustomerService, private router: Router) { }

  ngOnInit() {
    this.ProductFeedbackForm=this.formBuilder.group({
      userId:[{value:'3', disabled:false}],
      feedbackSubject:['',[Validators.required,Validators.maxLength(40),Validators.minLength(15)]],
      feedbackMessage:['',[Validators.required,Validators.maxLength(100),Validators.minLength(25)]],
      productId:[localStorage.productId]
    });
      
  }

  productFeedback(){
    this.save();
  }

  save(){
      this.submitted=true;
    if(this.ProductFeedbackForm.invalid){
      return;
    }
    else{
    console.log(this.ProductFeedbackForm.value);
    this.customerService.create(this.ProductFeedbackForm.value).subscribe(data => 
      {
        this.message=data;
        this.submitted1=true;
        console.log(this.message);        
      },
      err => 
      { console.log(err.stack);
      });
  }
  }


  
}
