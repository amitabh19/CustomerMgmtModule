package com.capstore.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.service.ServiceImpl;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	ServiceImpl serv;

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

	@PostMapping("/addFeedback")
	public boolean create(@RequestBody ProductFeedback pf) {
		return serv.createFeedback(pf);
	}

	@GetMapping("/getProductFeedback/{productId}")
	public ProductFeedback getFeedbackByProductId(@PathVariable int productId) {
		return serv.getFeedbackByProductId(productId);
	}
}
