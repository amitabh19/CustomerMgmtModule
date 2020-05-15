package com.capstore.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.User;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//merchant created
				MerchantDetails merchant1 = new MerchantDetails("Merchant 1", "merchant1", "m1234", "merchant1@gmail.com", "Merchant", true, "What is your age", "21", "7895421237", null, null, null, null, null, null, true, 4);
				MerchantDetails merchant2 = new MerchantDetails("Merchant 2", "merchant2", "m2234", "merchant2@gmail.com", "Merchant", true, "What is your age", "21", "7895221222", null, null, null, null, null, null, true, 3);
				
				merchantRepository.save(merchant1);
				merchantRepository.save(merchant2);
				
				//user created
				CustomerDetails customer1 = new CustomerDetails("Customer1", "customer1", "customer1", "customer1@gmail.com", "Customer", true, "Favourite Football team?", "Bayern", "9873426712", null, null, null, null, null, null, null, null);
				CustomerDetails customer2 = new CustomerDetails("Customer2", "customer2", "customer2", "customer2@gmail.com", "Customer", true, "Favourite Football team?", "Bayern", "9872426212", null, null, null, null, null, null, null, null);
				
				customerRepository.save(customer1);
				customerRepository.save(customer2);
				
				//add product to merchants
				Product product1 = new Product("Product1", "Some Location", 500, 4, 2, "Brand1", 100, "Product Info", 2, "Category1", true, true, true);
				Product product2 = new Product("Product2", "Some Location1", 550, 3, 2, "Brand2", 120, "Product Info2", 2, "Category2", true, true, true);
				Product product3 = new Product("Product3", "Some Location2", 505, 4, 2, "Brand3", 130, "Product Info3", 2, "Category3", true, true, true);
				
				Set<Product> merchant1Product = new HashSet<Product>();
				merchant1Product.add(product1);
				merchant1Product.add(product2);
				
				Set<Product> merchant2Product = new HashSet<Product>();
				merchant2Product.add(product3);
				
				merchant1.setProducts(merchant1Product);
				merchant2.setProducts(merchant2Product);
				
				merchantRepository.save(merchant1);
				merchantRepository.save(merchant2);
				
				
		
	}
	
}
