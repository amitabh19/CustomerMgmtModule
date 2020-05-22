import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { ProductFeedback } from '../ProductFeedback';
import { Router } from '@angular/router';
import { collectExternalReferences } from '@angular/compiler';

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
  check:boolean=false;
  

  constructor(private formBuilder: FormBuilder, private customerService:CustomerService, private router: Router) { }

  ngOnInit() {
    this.ProductFeedbackForm=this.formBuilder.group({
      userId:[{value:'3', disabled:false}],
      feedbackSubject:['',[Validators.required,Validators.maxLength(30),Validators.minLength(15)]],
      feedbackMessage:['',[Validators.required,Validators.maxLength(50),Validators.minLength(25)]],
      productId:[{value:'22', disabled: false}]
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
    let feedbackSubject=this.ProductFeedbackForm.controls.feedbackSubject.value;
    let feedbackMessage=this.ProductFeedbackForm.controls.feedbackMessage.value;
    let userId= this.ProductFeedbackForm.controls.userId.value;
    let productId=this.ProductFeedbackForm.controls.productId.value;
    console.log(this.ProductFeedbackForm.value);
     this.customerService.create(this.ProductFeedbackForm.value,this.ProductFeedbackForm.controls.userId.value).subscribe(data => 
        {
          this.message=data;
          alert("Feedback submitted");
          //console.log(this.message);        
        },
        err => 
        { console.log(err.stack);
        });
    }
  }


  
}
