package com.capstore.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.app.models.CustomerDetails;
import com.capstore.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerDetails getCustomerDetailsById(Integer id) {
		Optional<CustomerDetails> customerDetails = customerRepository.findById(id);
		CustomerDetails cd1=null;
		if(customerDetails.isPresent()) {
			cd1 = customerDetails.get();
		}
		return cd1;
		
	}

	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customer) {
		System.out.println("Customer Details Updated");
		return customerRepository.save(customer);
	}
	
	
}
