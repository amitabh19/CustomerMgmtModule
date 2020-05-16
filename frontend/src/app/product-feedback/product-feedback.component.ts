import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { ProductFeedback } from '../ProductFeedback';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-feedback',
  templateUrl: './product-feedback.component.html',
  styleUrls: ['./product-feedback.component.css']
})
export class ProductFeedbackComponent implements OnInit {

  ProductFeedback: ProductFeedback=new ProductFeedback();
  submitted: boolean=false;
  ProductFeedbackForm: FormGroup;
  msg1:any;
  message:boolean;
  

  constructor(private formBuilder: FormBuilder, private customerService:CustomerService, private router: Router) { }

  ngOnInit() {
    this.ProductFeedbackForm=this.formBuilder.group({
      feedbackSubject:['',[Validators.required,Validators.maxLength(30),Validators.minLength(15)]],
      feedbackMessage:['',[Validators.required,Validators.maxLength(50),Validators.minLength(25)]]
    });
  }

  productFeedback(){
    this.save();
  }

  newProductFeedback(): void{
    this.submitted=false;
    this.ProductFeedback=new ProductFeedback();
  }

  save(){
      this.submitted=true;
    if(this.ProductFeedbackForm.invalid){
      return;
    }
    else{
    let feedbackSubject=this.ProductFeedbackForm.controls.feedbackSubject.value;
    let feedbackMessage=this.ProductFeedbackForm.controls.feedbackMessage.value;
    
    this.customerService.create(this.ProductFeedback).subscribe(data => 
      {
        this.ProductFeedback=new ProductFeedback();
        this.message=data;
        prompt("Feedback submitted");
        console.log(this.message);
        
      },
      err => 
      { console.log(err.stack);
      });
    }
  }


  
}
