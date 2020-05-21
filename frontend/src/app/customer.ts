export class Customer {

    userId:Number;
    name:String;
    username:String;
    password:String;
    eMail:String;
    role:String;
    securityQuestion:String;
   securityAnswer:String;
   phoneNumber:String;
    alternatePhoneNumber:String;
    alternateEmail:String;
    address:String;
   //private orders:[];
    active:Boolean;
    customerCarts:[{
       cartId:Number,
       type:String,
       quantity:Number,
       productId:Number
       
   }];
  // private feedbacks:[];
   //private productFeedbacks:[];

}