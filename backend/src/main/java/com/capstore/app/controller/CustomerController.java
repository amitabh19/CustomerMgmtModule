package com.capstore.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.User;
import com.capstore.app.models.UserAddress;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.UserRepository;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private MerchantRepository merRepo;

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
@RequestMapping("/test")
public User index() {  
	Set<CommonFeedback> cCF = new HashSet<>();
	Set<ProductFeedback> cPF = new HashSet<>();
	Set<Order> orders = new HashSet<>();
	Set<Cart> cC = new HashSet<>();
	Set<UserAddress> addresses = new HashSet<>();
	User u = new CustomerDetails("lordstark", "kin", "jonsnow", "lord@winterfell", "customer", true, "u a b?", "no", "6969696969", "420420420", "lord@winterfell", "winterfell", cCF, cPF, orders, cC, addresses);
	userRepo.save(u);
	return userRepo.save(u);
} 

//amitabh
@GetMapping("/Cusers")
public List<CustomerDetails> getUsers()
{
	return custRepo.findAll();
}
	
public void function2(){
	//next push
}
}
