package com.capstore.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CustomerController {
	
	//random comment
	// krapiya yha apne functions bana dijiye
	//mike testing 


public void SahilKaFunction(){
	//sahil ka comment
}

//bht sahi
public void something()
{
}

//vishruti func
public void test()
{
}
//amitabh ka func
@RequestMapping("/")
public String index() {            
    return "first method";
} 

	
public void function2(){
	//next push
}
}
