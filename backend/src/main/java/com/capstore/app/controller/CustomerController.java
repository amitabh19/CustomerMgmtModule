package com.capstore.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	//random comment
	// krapiya yha apne functions bana dijiye
	//mike testing 
	
	@RequestMapping("/customers")
	public List<CustomerDetails> geAllMerchant() {
		return customerRepository.findAll(); 
	}
	
	@RequestMapping("customer/{id}")
	public CustomerDetails getMerchantById(@PathVariable int id) {
		return customerRepository.getOne(id);
	}
}
