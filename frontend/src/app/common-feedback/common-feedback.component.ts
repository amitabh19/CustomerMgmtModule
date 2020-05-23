import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';
import { CommonFeedback } from '../CommonFeedback';

@Component({
  selector: 'app-common-feedback',
  templateUrl: './common-feedback.component.html',
  styleUrls: ['./common-feedback.component.css']
})
export class CommonFeedbackComponent implements OnInit {

  submitted: boolean=false;
  submitted1: boolean=false;
  CommonFeedbackForm: FormGroup;
  message:any;
  check:boolean=false; 
  userId:number;
  productId: number;
  products:String[];
  constructor(private formBuilder: FormBuilder, private customerService:CustomerService, private router: Router) { }

  ngOnInit() {
    this.CommonFeedbackForm=this.formBuilder.group({
      userId:[{value:'3', disabled:false}],
      productId:[this.productId],
      productList:['',Validators.required],
      feedbackSubject:['',[Validators.required,Validators.maxLength(40),Validators.minLength(15)]],
      feedbackMessage:['',[Validators.required,Validators.maxLength(100),Validators.minLength(25)]]
    });
    
    this.customerService.getOrderedProductName(this.CommonFeedbackForm.controls.userId.value).subscribe(data => {
      this.products=data;      
      console.log(this.products);
    },
    err =>{
      console.log(err.stack);
    });
  }

  commonFeedback(){
    this.submitted=true;
    this.save();
  }
  onChange(){
    this.customerService.getProductIdByName(this.CommonFeedbackForm.controls.productList.value).subscribe(data =>
      {
        this.productId=data;
        console.log(this.productId);
      },
      err =>
      {
        console.log(err.stack);
      });
  }

  save(){
    if(this.CommonFeedbackForm.invalid){
      console.log("Invalid");
      return;
    }
    else{
    this.CommonFeedbackForm.controls.productId.setValue(this.productId);
    console.log(this.CommonFeedbackForm.value);
    this.customerService.createCommonFeedback(this.CommonFeedbackForm.value).subscribe(data => 
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
