package com.capstore.app.service;

import com.capstore.app.models.CustomerDetails;

public interface CustomerService {
	public CustomerDetails getCustomerDetailsById(Integer id);
	public CustomerDetails updateCustomerDetails(CustomerDetails customer);

}
